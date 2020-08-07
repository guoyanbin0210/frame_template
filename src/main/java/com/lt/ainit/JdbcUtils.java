package com.lt.ainit;

import com.lt.base.util.BeanRefUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.lt.ainit.JdbcColumnModelType.LONGTEXT;
import static com.lt.ainit.JdbcColumnModelType.getOneByName;

public class JdbcUtils {
    private final Logger LOGGER = LogManager.getLogger(getClass());

    public JdbcUtils(String package_name, String table_name, String annotation_name, String menu_parent_name) {
        this.PACKAGE_NAME = package_name;//项目包名
        this.TABLE_NAME = table_name;//表名
        this.ANNOTATION_NAME = annotation_name;//表名对应中文
        this.MENU_PARENT_NAME = menu_parent_name;//父级菜单名称
        initField();
    }


    public void doMain(
            ArrayList<JdbcColumnModel> jdbcColumnModels,
            boolean is_create_table,
            boolean is_create_view,
            boolean is_create_java) throws IOException, SQLException {
        if (is_create_table) {
            Connection connection = initCollection(url, username, password);
            String dro = "DROP TABLE IF EXISTS `" + TABLE_NAME + "`;";
            PreparedStatement preparedStatement = connection.prepareStatement(dro);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(createTable(TABLE_NAME, jdbcColumnModels));
            if (preparedStatement.executeUpdate() != 0) {
                LOGGER.error("创建失败");
            }
        }
        if (is_create_view) {
            createViewEditAndAdd(jdbcColumnModels, HTML_ADD_FILE,
                    "    $(document).ready(function () {doInsertChild($(\"#requestForm\"), {});});\n" +
                            "function closed(){\n" +
                            "        var index = parent.layer.getFrameIndex(window.name); //\n" +
                            "        parent.layer.close(index); // 关闭layer\n" +
                            "    }");
            createViewEditAndAdd(jdbcColumnModels, HTML_EDIT_FILE,
                    "    $(document).ready(function () {\n" +
                            "        doSelectOneByIdChild(function (data) {\n" +
                            createEditSetValue(jdbcColumnModels) +
                            "        });\n" +
                            "        doUpdateChild($(\"#requestForm\"), {})\n" +
                            "    });\n" +
                            "function closed(){\n" +
                            "        var index = parent.layer.getFrameIndex(window.name); //\n" +
                            "        parent.layer.close(index); // 关闭layer\n" +
                            "    }");
            createViewList(jdbcColumnModels);
        }
        if (is_create_java) {
            //创建文件
            creatModel(jdbcColumnModels);
            writeDAO();
            writeService();
            writeServiceImpl();
            writeMapper(jdbcColumnModels);
            writeController();
        }

    }

    /**
     * 仅用于创建表
     *
     * @param tableName
     * @return 返回创建表的sql
     */
    private String createTable(String tableName, ArrayList<JdbcColumnModel> jdbcColumnModels) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" CREATE TABLE `").append(tableName).append("` (");
        JdbcColumnModel item;
        for (int i = 0; i < jdbcColumnModels.size(); i++) {
            item = jdbcColumnModels.get(i);
            stringBuilder.append("`").append(item.getColumn_name()).append("` ");
            if (i == 0) {
                stringBuilder.append("varchar(255) NOT NULL ");//没用上 2018年10月26日 15:40:15
            } else {
                switch (item.getColumn_type()) {
                    case VARCHAR:
                        stringBuilder.append("varchar(").append(item.getColumn_size()).append(") NULL DEFAULT '' ");
                        break;
                    case DATETIME:
                        stringBuilder.append("datetime(").append(item.getColumn_size()).append(") NULL DEFAULT NULL ");
                        break;
                    case LONGTEXT:
                        stringBuilder.append("longtext NULL ");
                        break;
                    case INT:
                        stringBuilder.append("int(").append(item.getColumn_size()).append(") NULL DEFAULT 0 ");
                        break;
                    case DOUBLE:
                        stringBuilder.append("double(").append(item.getColumn_size()).append(") NULL DEFAULT 0 ");
                        break;
                }
            }
            stringBuilder.append(" COMMENT '").append(item.getColumn_comment()).append("',");
            if (i == jdbcColumnModels.size() - 1) {
                stringBuilder.append(" PRIMARY KEY (`id`) ");
            }
        }
        stringBuilder.append(")COMMENT = '").append(ANNOTATION_NAME).append("';");
        return stringBuilder.toString();
    }

    public void initDateInfo() throws SQLException {
        Connection connection = initCollection(url, username, password);
        Statement statement = null;
        statement = connection.createStatement();
        int i = statement.executeUpdate("CREATE DATABASE " + TABLE_SCHEMA + " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;");
        LOGGER.info("创建数据库：【" + TABLE_SCHEMA + "】" + (i != 0 ? "成功" : "失败"));
        if (i != 0) {
            SQLExec sqlExec = new SQLExec();
            // 设置数据库参数
            sqlExec.setDriver(DRIVER);
            sqlExec.setUrl(url);
            sqlExec.setUserid(username);
            sqlExec.setPassword(password);
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/sql/lt_patrol.sql");
            sqlExec.setSrc(file);
            sqlExec.setPrint(true); // 设置是否输出
            // 输出到文件 sql.out 中；不设置该属性，默认输出到控制台
            // sqlExec.setOutput(new File("d:/script/sql.out"));
            sqlExec.setProject(new Project());  // 要指定这个属性，不然会出错
            sqlExec.execute();
        }
        connection.close();
    }


    /**
     * 生成POJO文件
     *
     * @throws IOException
     */
    private void creatModel(ArrayList<JdbcColumnModel> jdbcColumnModels) throws IOException {
        // 指定要写入的文件
        File file = checkFileIsExists(MODEL_PATH, MODEL_NAME_JAVA_FILE);
        if (file == null) return;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        StringBuilder waitString = new StringBuilder();
        waitString
                .append("package ").append(BASE_PACKAGE_NAME).append(".model;\n")
                .append("import com.lt.base.model.BaseModel;\n" +
                        "import io.swagger.annotations.ApiModelProperty;\n" +
                        "import org.springframework.stereotype.Component;\n" +
                        "import java.util.Date;\n")
                .append("/**\n")
                .append(" * * Created with GuoYanBin.\n")
                .append(" * Description:").append(ANNOTATION_NAME).append("\n")
                .append(" * Create Time: ").append(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date())).append("\n")
                .append(" */\n")
                .append("@Component\n")
                .append("public class ").append(MODEL_NAME_JAVA).append(" extends BaseModel{\n");

        boolean tag = true;
        String column_name;
        String column_comment;
        //声明注释，用于提供给前端
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            column_name = jdbcColumnModel.getColumn_name();
          /*  if (column_name.equals("base_sort")) {
                tag = true;
                continue;
            }*/
            switch (column_name) {
                case "id":
                    continue;
                case "create_by":
                    continue;
                case "create_time":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            column_comment = jdbcColumnModel.getColumn_comment();
            if (tag) {
                waitString.append("    //").append(column_comment).append(":").append(column_name);
                switch (jdbcColumnModel.getColumn_type()) {
                    case DATETIME:
                        waitString.append(",类型：日期\n");
                        break;
                    case INT:
                        waitString.append(",类型：整型\n");
                        break;
                    case DOUBLE:
                        waitString.append(",类型：浮点型\n");
                        break;
                    default:
                        waitString.append(",类型：字符串\n");
                        break;
                }
                waitString.append("\n");
            }

        }
        tag = true;
        //属性声明
        int index = 0;
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            column_name = jdbcColumnModel.getColumn_name();
            switch (column_name) {
                case "id":
                    continue;
                case "create_by":
                    continue;
                case "create_time":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            /*if (column_name.equals("base_sort")) {
                tag = true;
                continue;
            }*/
            column_comment = jdbcColumnModel.getColumn_comment();
            if (tag) {
                waitString
                        .append("    /**\n    * ")
                        .append(column_comment).append("\n")
                        .append("    */\n")
                        .append("    @ApiModelProperty(value =").append("\"" + column_comment + "\"").append(",position = " + index + "").append(")\n")
                        .append("    private ").append(jdbcColumnModel.getColumn_type().getName_java()).append(" ").append(column_name).append(";\n\n");
                index++;
            }
        }
        tag = true;
        //get set
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            column_name = jdbcColumnModel.getColumn_name();
            /*if (column_name.equals("base_sort")) {
                tag = true;
                continue;
            }*/
            switch (column_name) {
                case "id":
                    continue;
                case "create_by":
                    continue;
                case "create_time":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            if (tag) {
                String filedType = jdbcColumnModel.getColumn_type().getName_java();
                waitString
                        .append("    public void ").append(BeanRefUtil.parSetName(column_name)).append("(").append(filedType).append(" ").append(column_name).append("){")
                        .append("        this.").append(column_name).append("=").append(column_name).append(";")
                        .append("    }\n \n");


                waitString
                        .append("    public ").append(filedType).append(" ").append(BeanRefUtil.parGetName(column_name)).append("(){")
                        .append("        return ").append(column_name).append(";")
                        .append("    }\n \n");

            }
        }

        waitString.append("}");
        bufferedWriter.write(waitString.toString());
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    /**
     * @param jdbcColumnModels
     */
    public void writeMapper(ArrayList<JdbcColumnModel> jdbcColumnModels) throws IOException {

        File file = checkFileIsExists(RESOURCE_MAPPER_PATH, MAPPER_NAME_XML_FILE);
        if (file == null) return;

        Document document = DocumentHelper.createDocument();

        initMapperXml(jdbcColumnModels, document);

        OutputFormat format = OutputFormat.createPrettyPrint();

        format.setEncoding("UTF-8");

        Writer fileWriter = new FileWriter(file);
        // 创建XMLWriter对象
        XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
        //设置不自动进行转义
        xmlWriter.setEscapeText(false);
        // 生成XML文件
        xmlWriter.write(document);

        //关闭资源
        fileWriter.close();
        xmlWriter.close();
    }

    /**
     * 初始化 mapper.xml
     *
     * @param jdbcColumnModels
     * @param document
     */
    private void initMapperXml(ArrayList<JdbcColumnModel> jdbcColumnModels, Document document) {
        document.setXMLEncoding("UTF-8");
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd", null);
        Element mapper = document.addElement("mapper");
        mapper.addAttribute("namespace", PACKAGE_DAO_PATH + "." + DAO_NAME_JAVA);
        Element resultMap = mapper.addElement("resultMap");
        initXmlResultMap(jdbcColumnModels, resultMap);
        initSelectAll(jdbcColumnModels, mapper);
        initBase_Column_List(jdbcColumnModels, mapper);
        initBase_property_List(jdbcColumnModels, mapper);
        initBase_update_List(jdbcColumnModels, mapper);
        initBase_select_List(jdbcColumnModels, mapper);
        initBase_select_like_List(jdbcColumnModels, mapper);

        Element insert = mapper.addElement("insert");
        insert.addAttribute("id", "insert");
        insert.setText("\nINSERT INTO " + TABLE_NAME + " (<trim suffixOverrides=\",\"><include refid=\"Base_Column_List\"/></trim>) VALUES (<trim suffixOverrides=\",\"><include refid=\"Base_property_List\"/></trim>);");
        Element delete = mapper.addElement("delete");
        delete.addAttribute("id", "delete");
        delete.setText("\nDELETE FROM " + TABLE_NAME + " WHERE  id = #{id};");

        Element update = mapper.addElement("update");
        update.addAttribute("id", "update");
        update.setText("\nUPDATE " + TABLE_NAME + " SET <trim suffixOverrides=\",\"><include refid=\"Base_update_List\"/></trim> WHERE id = #{id};");

        Element getOne = mapper.addElement("select");
        getOne.addAttribute("id", "selectById");
        getOne.addAttribute("resultMap", "baseModel");
        getOne.addAttribute("useCache", "true");
        getOne.setText("\nSELECT <include refid=\"select_all\"/> FROM " + TABLE_NAME + " WHERE id =#{id};");


        Element getAllByModel = mapper.addElement("select");
        getAllByModel.addAttribute("id", "selectListByModel");
        getAllByModel.addAttribute("resultMap", "baseModel");
        getAllByModel.addAttribute("useCache", "true");
        getAllByModel.setText("\nSELECT <include refid=\"select_all\"/> FROM " + TABLE_NAME + "<where> <include refid=\"Base_select_List\"/> </where>  ORDER BY create_time DESC");


        Element getAll = mapper.addElement("select");
        getAll.addAttribute("id", "selectAll");
        getAll.addAttribute("resultMap", "baseModel");
        getAll.addAttribute("useCache", "true");
        getAll.setText("\nSELECT <include refid=\"select_all\"/> FROM " + TABLE_NAME + " ORDER BY  create_time DESC");


        Element getAllByKey = mapper.addElement("select");
        getAllByKey.addAttribute("id", "selectListByKeyWord");
        getAllByKey.addAttribute("resultMap", "baseModel");
        getAllByKey.addAttribute("useCache", "true");
        getAllByKey.setText("\nSELECT <include refid=\"select_all\"/> FROM " + TABLE_NAME + "<where> CONCAT(<include refid=\"Base_select_like_List\"/>) LIKE concat(concat('%',#{keyWord}),'%')</where>  ORDER BY create_time DESC");
    }


    private static void initBase_select_like_List(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {

        Element initBase_select_List = mapper.addElement("sql");
        initBase_select_List.addAttribute("id", "Base_select_like_List");
        StringBuilder text = new StringBuilder();
        text.append("<trim suffixOverrides=\",\">\n");
        boolean tag = false;
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
           /* if (!tag) {
                if (jdbcColumnModel.getColumn_name().equals("base_sort")) {
                    tag = true;
                }
                continue;
            }*/
            text.append("IFNULL(").append(jdbcColumnModel.getColumn_name()).append(", ''),\n");
        }
        text.append("</trim>");
        initBase_select_List.setText(text.toString());

    }

    private static void initBase_select_List(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {
        Element initBase_select_List = mapper.addElement("sql");
        initBase_select_List.addAttribute("id", "Base_select_List");
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            //  <if test="create_by!=null">AND create_by = #{create_by}</if>
            Element initIf = initBase_select_List.addElement("if");
            initIf.addAttribute("test", jdbcColumnModel.getColumn_name() + "!=null");
            initIf.setText("AND " + jdbcColumnModel.getColumn_name() + "=#{" + jdbcColumnModel.getColumn_name() + "}");
        }
    }

    private static void initBase_update_List(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {
        Element initBase_update_List = mapper.addElement("sql");
        initBase_update_List.addAttribute("id", "Base_update_List");
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            // <if test="create_by!=null">create_by = #{create_by},</if>
            Element initIf = initBase_update_List.addElement("if");
            initIf.addAttribute("test", jdbcColumnModel.getColumn_name() + "!=null");
            initIf.setText(jdbcColumnModel.getColumn_name() + "=#{" + jdbcColumnModel.getColumn_name() + "},");
        }

    }

    private static void initBase_property_List(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {
        Element initBase_property_List = mapper.addElement("sql");
        initBase_property_List.addAttribute("id", "Base_property_List");
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            // <if test="id!=null">#{id},</if>
            Element initIf = initBase_property_List.addElement("if");
            initIf.addAttribute("test", jdbcColumnModel.getColumn_name() + "!=null");
            initIf.setText("#{" + jdbcColumnModel.getColumn_name() + "},");
        }
    }

    private static void initBase_Column_List(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {
        Element initBase_Column_List = mapper.addElement("sql");
        initBase_Column_List.addAttribute("id", "Base_Column_List");
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            Element initIf = initBase_Column_List.addElement("if");
            initIf.addAttribute("test", jdbcColumnModel.getColumn_name() + "!=null");
            initIf.setText(jdbcColumnModel.getColumn_name() + ",");
        }
    }

    private static void initSelectAll(ArrayList<JdbcColumnModel> jdbcColumnModels, Element mapper) {
        Element initBase_Column_List = mapper.addElement("sql");
        initBase_Column_List.addAttribute("id", "select_all");
        StringBuilder testBody = new StringBuilder();
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            testBody.append(jdbcColumnModel.getColumn_name()).append(",\r\n");
        }
        if (!testBody.toString().equals("")) {
            testBody = new StringBuilder(testBody.substring(0, testBody.lastIndexOf(",")));
        }

        initBase_Column_List.setText(testBody.toString());

    }

    private void initXmlResultMap(ArrayList<JdbcColumnModel> jdbcColumnModels, Element resultMap) {
        resultMap.addAttribute("id", "baseModel");
        resultMap.addAttribute("type", MAPPER_RESULT_MAP_TYPE);
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            Element initId = resultMap.addElement("id");
            initId.addAttribute("column", jdbcColumnModel.getColumn_name());
            initId.addAttribute("property", jdbcColumnModel.getColumn_name());
        }
    }

    private void writeDAO() throws IOException {

        File file = checkFileIsExists(DAO_PATH, DAO_NAME_JAVA_FILE);
        if (file == null) return;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + BASE_PACKAGE_NAME + ".dao;\n");
        bufferedWriter.newLine();
        bufferedWriter.write(
                "import " + BASE_PACKAGE_NAME + ".model." + MODEL_NAME_JAVA + ";\n" +
                        "import com.lt.base.dao.BaseDao;\n" +
                        "import org.apache.ibatis.annotations.Mapper;\n" +
                        "/**\n" +
                        " * * Created with GuoYanBin.\n" +
                        " * Description:" + ANNOTATION_NAME + " mapper\n" +
                        " * Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                        " * Time: " + new SimpleDateFormat("hh:mm").format(new Date()) + "\n" +
                        " */\n" +
                        "@Mapper\n" +
                        "public interface " + DAO_NAME_JAVA + " extends BaseDao<" + MODEL_NAME_JAVA + ">{\n" +
                        "}"
        );
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    private File checkFileIsExists(String dao_path, String dao_name_java) throws IOException {
        File file = new File(dao_path, dao_name_java);
        if (!file.exists()) {
            File fileParent = new File(dao_path);
            if (!fileParent.exists()) {
                if (!fileParent.mkdirs()) {
                    LOGGER.error(fileParent.getAbsolutePath() + ",创建文件夹失败");
                    return null;
                }
            }
            if (!file.createNewFile()) {
                LOGGER.error(file.getAbsolutePath() + ",创建文件失败");
                return null;
            }
        }
        return file;
    }

    /**
     * .
     *
     * @throws IOException
     */
    private void writeService() throws IOException {
        File file = checkFileIsExists(SERVICE_PATH, SERVICE_NAME_JAVA_FILE);
        if (file == null) return;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(
                "package " + BASE_PACKAGE_NAME + ".service;\n" +
                        "import " + BASE_PACKAGE_NAME + ".model." + MODEL_NAME_JAVA + ";\n" +
                        "import com.lt.base.service.BaseService;\n" +
                        "/**\n" +
                        " * * Created with GuoYanBin.\n" +
                        " * Description:" + ANNOTATION_NAME + " service\n" +
                        " * Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                        " * Time: " + new SimpleDateFormat("hh:mm").format(new Date()) + "\n" +
                        " */\n" +
                        "public interface " + SERVICE_NAME_JAVA + " extends BaseService<" + MODEL_NAME_JAVA + ">{}"
        );
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    private void writeServiceImpl() throws IOException {
        File file = checkFileIsExists(SERVICE_IMPL_PATH, SERVICE_IMPL_NAME_JAVA_FILE);
        if (file == null) return;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(
                "package " + BASE_PACKAGE_NAME + ".service.impl;\n" +
                        "import " + BASE_PACKAGE_NAME + ".dao." + DAO_NAME_JAVA + ";\n" +
                        "import " + BASE_PACKAGE_NAME + ".model." + MODEL_NAME_JAVA + ";\n" +
                        "import " + BASE_PACKAGE_NAME + ".service." + SERVICE_NAME_JAVA + ";\n" +
                        "import com.lt.base.dao.BaseDao;\n" +
                        "import com.lt.base.service.impl.BaseServiceImpl;\n" +
                        "import org.springframework.stereotype.Service;\n" +
                        "import javax.annotation.Resource;\n" +
                        "/**\n" +
                        " * * Created with GuoYanBin.\n" +
                        " * Description:" + ANNOTATION_NAME + " serviceImpl\n" +
                        " * Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                        " * Time: " + new SimpleDateFormat("hh:mm").format(new Date()) + "\n" +
                        " */\n" +
                        "@Service\n" +
                        "public class " + SERVICE_IMPL_NAME_JAVA + " extends BaseServiceImpl<" + MODEL_NAME_JAVA + "> implements " + SERVICE_NAME_JAVA + "{\n" +
                        "    @Resource \n" +
                        "    private " + DAO_NAME_JAVA + " dao;\n" +
                        "    @Override\n" +
                        "    public BaseDao<" + MODEL_NAME_JAVA + "> getBaseDao() {\n" +
                        "        return dao;\n" +
                        "    }\n" +
                        "}"
        );
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * 创建 Controller
     * JdbcControllerUtil.writeController(basePackageName,outputPath)
     *
     * @throws IOException
     */
    private void writeController() throws IOException {

        BufferedOutputStream bos;
        File file = new File(CONTROLLER_PATH, CONTROLLER_NAME_JAVA_FILE);
        if (!file.exists()) {
            File fileParent = new File(CONTROLLER_PATH);
            if (fileParent.mkdirs()) {
                if (!file.createNewFile()) {
                    LOGGER.error(file.getName() + "，文件创建失败");
                    return;
                }
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            String strContent =
                    "package " + BASE_PACKAGE_NAME + ".controller;\n" +
                            "import com.lt.base.aop.ControllerMethodLog;\n" +
                            "import com.lt.base.model.BaseModel;\n" +
                            "import springfox.documentation.annotations.ApiIgnore;\n" +
                            "import com.lt.base.model.SysLoginModel;\n" +
                            "import com.lt.base.service.SysLoginService;\n" +
                            "import com.lt.base.util.BeanRefUtil;\n" +
                            "import com.lt.base.poi.PoiExcelUtils;\n" +
                            "import org.slf4j.Logger;\n" +
                            "import org.slf4j.LoggerFactory;\n" +
                            "import org.springframework.web.bind.annotation.*;\n" +
                            "import org.springframework.web.multipart.MultipartFile;\n" +
                            "\n" +
                            "import javax.annotation.Resource;\n" +
                            "import javax.servlet.http.HttpServletRequest;\n" +
                            "import javax.servlet.http.HttpServletResponse;\n" +
                            "import java.io.IOException;\n" +
                            "import java.util.HashMap;\n" +
                            "import java.util.List;\n" +
                            "/**\n" +
                            " * * Created with GuoYanBin.\n" +
                            " * Description:\n" +
                            " * Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n" +
                            " * Time: " + new SimpleDateFormat("hh:mm").format(new Date()) + "\n" +
                            " */\n" +
                            "@RestController\n" +
                            "@ApiIgnore\n" +
                            "public class " + CONTROLLER_NAME_JAVA + " extends BaseController{\n" +
                            "    private Logger LOGGER = LoggerFactory.getLogger(getClass());\n" +
                            "}\n";
            bos.write(strContent.getBytes());
            bos.flush();
            bos.close();
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        Long length = file.length();
        byte[] bytes = new byte[length.intValue()];
        int read = bis.read(bytes);
        bis.close();
        String temp = new String(bytes);

        String strContent = temp.substring(0, temp.lastIndexOf("}"));
        strContent +=
                "    //---------------------------" + ANNOTATION_NAME + "-----------------------start\n" +
                        "    @Resource\n" +
                        "    private " + SERVICE_NAME_JAVA + " " + SERVICE_Instance + ";\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-插入\")\n" +
                        "    @PostMapping(\"" + DATA_URL_insert + "\")\n" +
                        "    public HashMap insert_" + TABLE_NAME_URL + "(HttpServletRequest request) {\n" +
                        "        return insert(request, " + SERVICE_Instance + ", new " + MODEL_NAME_JAVA + "());\n" +
                        "    }\n" +
                        "\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-删除\")\n" +
                        "    @PostMapping(\"" + DATA_URL_delete + "\")\n" +
                        "    public HashMap deleteById_" + TABLE_NAME_URL + "(@RequestParam(\"id\") String id) {\n" +
                        "        return delete(" + SERVICE_Instance + ", id);\n" +
                        "    }\n" +
                        "\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-更新\")\n" +
                        "    @PostMapping(\"" + DATA_URL_update + "\")\n" +
                        "    public HashMap update_" + TABLE_NAME_URL + "(HttpServletRequest request) {\n" +
                        "        return update(request, " + SERVICE_Instance + ", new " + MODEL_NAME_JAVA + "());\n" +
                        "    }\n" +
                        "\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-查询一个\")\n" +
                        "    @PostMapping(\"" + DATA_URL_selectById + "\")\n" +
                        "    public HashMap selectById_" + TABLE_NAME_URL + "(@RequestParam(\"id\") String id) {\n" +
                        "        return selectById(" + SERVICE_Instance + ", id);\n" +
                        "    }\n" +
                        "\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-查询多个\")\n" +
                        "    @PostMapping(\"/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/selectAll\")\n" +
                        "    public HashMap selectList_" + TABLE_NAME_URL + "(HttpServletRequest request) {\n" +
                        "        return selectListByCondition(request, " + SERVICE_Instance + ", new " + MODEL_NAME_JAVA + "());\n" +
                        "    }\n" +
                        "\n" +
                        "    @ControllerMethodLog(remark = \"" + ANNOTATION_NAME + "-查询分页\")\n" +
                        "    @PostMapping(\"" + DATA_URL_selectListByPageHelper + "\")\n" +
                        "    public HashMap selectListByPageHelper_" + TABLE_NAME_URL + "(HttpServletRequest request) {\n" +
                        "        return selectListByPageHelper(request, " + SERVICE_Instance + ", new " + MODEL_NAME_JAVA + "());\n" +
                        "    }\n" +
                        "    //---------------------------" + ANNOTATION_NAME + "-----------------------end\n" +
                        "}\n";
        bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(strContent.getBytes());
        bos.flush();
        bos.close();

    }


    private String createEditSetValue(ArrayList<JdbcColumnModel> jdbcColumnModels) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean tag = true;
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
           /* if (jdbcColumnModel.getColumn_name().equals("base_sort")) {
                tag = true;
                continue;
            }*/
            String name = jdbcColumnModel.getColumn_name();
            switch (name) {
                case "id":
                    continue;
                case "create_by":
                    continue;
                case "create_time":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            if (tag) {
                stringBuilder.append("            ").append("$(\"#").append(jdbcColumnModel.getColumn_name()).append("\").val(data.").append(jdbcColumnModel.getColumn_name()).append(");\n");
            }
        }
        return stringBuilder.toString();
    }

    private void createViewList(ArrayList<JdbcColumnModel> jdbcColumnModels) throws IOException {
        File file = checkFileIsExists(RESOURCE_HTML_PATH, HTML_LIST_FILE);
        if (file == null) return;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(
                "<!DOCTYPE HTML>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"renderer\" content=\"webkit|ie-comp|ie-stand\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                        "    <meta name=\"viewport\"\n" +
                        "          content=\"width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\"/>\n" +
                        "    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\"/>\n" +
                        "    <title>" + TABLE_ChName + "</title>\n" +
                        "    <script type=\"text/javascript\" src=\"/js/initCss.js\"></script>\n" +
                        "    <script type=\"text/javascript\" src=\"/js/initJs.js\"></script>\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"/lib/pagination/pagination.css\"/>\n" +
                        "    <script type=\"text/javascript\" src=\"/lib/pagination/jquery.pagination.js\"></script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<nav class=\"breadcrumb\"><i class=\"Hui-iconfont\">&#xe67f;</i> 首页\n" +
                        "    <span class=\"c-gray en\">&gt;</span>" + MENU_PARENT_NAME + "<span class=\"c-gray en\">&gt;</span> " + TABLE_ChName + "\n" +
                        "    <a class=\"btn btn-success radius r\" style=\"line-height:1.6em;margin-top:3px\"\n" +
                        "       href=\"javascript:location.replace(location.href);\" title=\"刷新\">\n" +
                        "        <i class=\"Hui-iconfont\">&#xe68f;\n" +
                        "        </i>\n" +
                        "    </a>\n" +
                        "</nav>\n" +
                        "<div class=\"page-container\">\n" +
                        "    <div class=\"text-c\">\n" +
                        "        <div class=\"btn-group\">\n" +
                        "            <input type=\"text\" name=\"\" id=\"in_search_text\" placeholder=\"请输入你想查询的内容\" style=\"width:250px\"\n" +
                        "                   class=\"input-text\">\n" +
                        "            <button name=\"\" id=\"btn_search\" class=\"btn btn-success\" type=\"submit\"><i class=\"Hui-iconfont\">&#xe665;</i>\n" +
                        "                搜索\n" +
                        "            </button>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"cl pd-5 bg-1 bk-gray mt-20\">\n" +
                        "        <span class=\"l\" id=\"btn_group\">\n" +
                        "            <a class=\"btn btn-primary radius\" click-info=\"add\"><i class=\"Hui-iconfont\">&#xe600;</i>添加 </a>\n" +
                        "            <a class=\"btn disabled radius multiple-choice\" click-info=\"delete\"><i\n" +
                        "                    class=\"Hui-iconfont\">&#xe6e2;</i>删除</a>\n" +
                        "            <a class=\"btn disabled radius single-choice\" click-info=\"edit\"><i class=\"Hui-iconfont\">&#xe60c;</i>编辑</a>\n" +
                        "        </span>\n" +
                        "    </div>\n" +
                        "    <div class=\"cl pd-5 bg-1 bk-gray mt-20\">\n" +
                        "        <span class=\"r\">数据总数: <span id=\"show_total\">暂未查询到数据</span></span>\n" +
                        "    </div>\n" +
                        "    <div class=\"mt-20\">\n" +
                        "        <table class=\"table table-border table-bordered table-hover table-bg table-sort\" id=\"\">\n" +
                        "            <thead>\n" +
                        "            <tr class=\"text-c text-overflow\">\n" +
                        "                <th width=\"5%\"><input type=\"checkbox\" id=\"checkBoxGroup\"></th>\n" +
                        createListTableHead(jdbcColumnModels) +
                        "            </tr>\n" +
                        "            </thead>\n" +
                        "            <tbody id=\"mytbody\">\n" +
                        "            </tbody>\n" +
                        "        </table>\n" +
                        "        <span class=\"r mt-10 mb-20\">\n" +
                        "            <div class=\"M-box3\"></div>\n" +
                        "        </span>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "    initPath(\n" +
                        "        '" + DATA_URL_selectListByPageHelper + "',\n" +
                        "        '" + DATA_URL_delete + "',\n" +
                        "        '" + DATA_URL_update + "',\n" +
                        "        '" + DATA_URL_insert + "',\n" +
                        "        '" + DATA_URL_selectById + "',\n" +
                        "        '" + VIEW_PATH_ADD + "',\n" +
                        "        '" + VIEW_PATH_EDIT + "',\n" +

                        "    );\n" +
                        "    $(document).ready(function () {\n" +
                        "        doSelectList(selectListParams, initAppendHtml);\n" +
                        "    });\n" +
                        "    function initAppendHtml(item) {\n" +
                        "        return '<tr class=\"text-c\" id=' + item.id + '>' +\n" +
                        "            '<td><input type=\"checkbox\" class=\"checkItemTag\" ></td>' +\n" +
                        createListTableBody(jdbcColumnModels) +
                        "            '</tr>';\n" +
                        "    }\n" +
                        "</script>\n" +
                        "</body>\n" +
                        "</html>"
        );


        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private String createListTableHead(ArrayList<JdbcColumnModel> jdbcColumnModels) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean tag = true;
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
            if (LONGTEXT.equals(jdbcColumnModel.getColumn_type())) {
                continue;
            }
         /*   if (jdbcColumnModel.getColumn_name().equals("base_sort")) {
                tag = true;
                continue;
            }*/
            String name = jdbcColumnModel.getColumn_name();
            switch (name) {
                case "id":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            if (tag)
                stringBuilder.append("                <th>").append(jdbcColumnModel.getColumn_comment()).append("</th>\n");
        }
        return stringBuilder.toString();
    }

    private String createListTableBody(ArrayList<JdbcColumnModel> jdbcColumnModels) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean tag = true;
        for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
           /* if (jdbcColumnModel.getColumn_name().equals("base_sort")) {
                tag = true;
                continue;
            }*/
            String name = jdbcColumnModel.getColumn_name();
            switch (name) {
                case "id":
                    continue;
                case "modify_by":
                    continue;
                case "modify_time":
                    continue;
            }
            if (tag)
                switch (jdbcColumnModel.getColumn_type()) {
                    case DATETIME:
                        stringBuilder.append("                '<td>' + myFormat(item.").append(jdbcColumnModel.getColumn_name()).append(", \"yyyy-MM-dd HH:mm:ss\") + '</td>' +\n");
                        break;
                    case LONGTEXT:
                        continue;
                    default:
                        stringBuilder.append("                '<td>' + item.").append(jdbcColumnModel.getColumn_name()).append(" + '</td>' +\n");
                }

        }
        return stringBuilder.toString();
    }

    /**
     * 添加/编辑
     *
     * @param jdbcColumnModels
     * @param fileName
     * @param js
     * @throws IOException
     */
    private void createViewEditAndAdd(ArrayList<JdbcColumnModel> jdbcColumnModels, String fileName, String js) throws IOException {

        File file = checkFileIsExists(RESOURCE_HTML_PATH, fileName);

        if (file == null) return;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(
                "<!DOCTYPE HTML>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"renderer\" content=\"webkit|ie-comp|ie-stand\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                        "    <meta name=\"viewport\"\n" +
                        "          content=\"width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\"/>\n" +
                        "    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\"/>\n" +
                        "    <title>" + TABLE_ChName + "</title>\n" +
                        "    <script type=\"text/javascript\" src=\"/js/initCss.js\"></script>\n" +
                        "    <script type=\"text/javascript\" src=\"/js/initJs.js\"></script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<article class=\"page-container\">\n" +
                        "    <form action=\"\" method=\"post\" class=\"form form-horizontal\" id=\"requestForm\">\n" +
                        createItemField(jdbcColumnModels) +
                        "    </form>\n" +
                        "</article>\n" +
                        "<script type=\"text/javascript\">\n" +
                        js +
                        "</script>\n" +
                        "</body>\n" +
                        "</html>"
        );

        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    private String createItemField(ArrayList<JdbcColumnModel> jdbcColumnModels) {
        StringBuilder stringBuilder = new StringBuilder();
        JdbcColumnModelType column_type;
        boolean tag = true;
        if (jdbcColumnModels.size() < 100) {
            for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
                column_type = jdbcColumnModel.getColumn_type();
               /* if (jdbcColumnModel.getColumn_name().equals("base_status")) {
                    tag = true;
                    continue;
                }*/
                String name = jdbcColumnModel.getColumn_name();
                switch (name) {
                    case "id":
                        continue;
                    case "create_by":
                        continue;
                    case "create_time":
                        continue;
                    case "modify_by":
                        continue;
                    case "modify_time":
                        continue;
                }
                if (tag)
                    switch (column_type) {
                        case VARCHAR:
                            if (jdbcColumnModel.getDataList() != null && jdbcColumnModel.getDataList().size() > 0) {
                                stringBuilder.append(
                                        "        <div class=\"row cl\">\n" +
                                                "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                                "            <div class=\"formControls col-xs-8 col-sm-9\"> \n" +
                                                "               <span class=\"select-box\">\n" +
                                                "                   <select class=\"select\"  id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\">\n"
                                );
                                for (Map.Entry<String, String> strinlttringEntry : jdbcColumnModel.getDataList().entrySet()) {
                                    String key = strinlttringEntry.getKey();
                                    String value = strinlttringEntry.getValue();
                                    stringBuilder.append("                       <option value=\"" + key + "\">" + value + "</option>\n");
                                }

                                stringBuilder.append(
                                        "                   </select>\n" +
                                                "               </span>\n" +
                                                "             </div>\n" +
                                                "        </div>\n"
                                );
                            } else if (jdbcColumnModel.isIs_textarea()) {
                                stringBuilder.append(

                                        "        <div class=\"row cl\">\n" +
                                                "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                                "            <div class=\"formControls col-xs-8 col-sm-9\">\n" +
                                                "                <textarea type=\"text\" class=\"textarea\"  id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\"></textarea>\n" +
                                                "            </div>\n" +
                                                "        </div>\n"
                                );
                            } else {
                                stringBuilder.append(
                                        "        <div class=\"row cl\">\n" +
                                                "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                                "            <div class=\"formControls col-xs-8 col-sm-9\">\n" +
                                                "                <input type=\"text\" class=\"input-text\"  id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\"" + " required>\n" +
                                                "            </div>\n" +
                                                "        </div>\n"
                                );
                            }
                            break;
                        case DATETIME:
                            stringBuilder.append(
                                    "        <div class=\"row cl\">\n" +
                                            "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                            "            <div class=\"formControls col-xs-8 col-sm-9\">\n" +
                                            "                <input type=\"text\"\n" +
                                            "                       onfocus=\"WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})\"\n" +
                                            "                       id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\" class=\"input-text Wdate\">\n" +
                                            "            </div>\n" +
                                            "        </div>\n"
                            );
                            break;
                        case LONGTEXT:
                            stringBuilder.append(
                                    "        <div class=\"row cl\">\n" +
                                            "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                            "            <div class=\"formControls col-xs-8 col-sm-9\">\n" +
                                            "                <input type=\"text\" class=\"input-text\"  id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\">\n" +
                                            "            </div>\n" +
                                            "        </div>\n"
                            );
                            break;
                        case INT:
                        case DOUBLE:
                            stringBuilder.append(
                                    "        <div class=\"row cl\">\n" +
                                            "            <label class=\"form-label col-xs-4 col-sm-2\" for=\"" + jdbcColumnModel.getColumn_name() + "\"><span class=\"c-red\">*</span>" + jdbcColumnModel.getColumn_comment() + "：</label>\n" +
                                            "            <div class=\"formControls col-xs-8 col-sm-9\">\n" +
                                            "                <input type=\"number\" class=\"input-text\"  id=\"" + jdbcColumnModel.getColumn_name() + "\" name=\"" + jdbcColumnModel.getColumn_name() + "\">\n" +
                                            "            </div>\n" +
                                            "        </div>\n"
                            );
                            break;
                    }


            }


        } else {
            int i = 0;
            stringBuilder.append("        <div class=\"row cl\">\n" +
                    "            <div class=\"col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2\">\n" +
                    "                <table class=\"table table-border table-bordered table-striped \">\n" +
                    "                    <tbody>");
            for (JdbcColumnModel jdbcColumnModel : jdbcColumnModels) {
                column_type = jdbcColumnModel.getColumn_type();
                String column_name = jdbcColumnModel.getColumn_name();
              /*  if (column_name.equals("base_status")) {
                    tag = true;
                    continue;
                }*/
                String name = jdbcColumnModel.getColumn_name();
                switch (name) {
                    case "id":
                        continue;
                    case "create_by":
                        continue;
                    case "create_time":
                        continue;
                    case "modify_by":
                        continue;
                    case "modify_time":
                        continue;
                }
                if (tag) {
                    if (i == 0) {
                        stringBuilder.append("<tr>\n");
                    }
                    String column_comment = jdbcColumnModel.getColumn_comment();
                    stringBuilder.append(
                            "                        <td width=\"20%\"><code><span class=\"c-red\">*</span>" + column_comment + "</code></td>\n" +
                                    "                        <td width=\"30%\">\n");
                    switch (column_type) {
                        case VARCHAR:
                            if (jdbcColumnModel.getDataList() != null && jdbcColumnModel.getDataList().size() > 0) {
                                stringBuilder.append(

                                        "               <span class=\"select-box\">\n" +
                                                "                   <select class=\"select  radius\"  id=\"" + column_name + "\" name=\"" + column_name + "\">\n"
                                );
                                for (Map.Entry<String, String> strinlttringEntry : jdbcColumnModel.getDataList().entrySet()) {
                                    String key = strinlttringEntry.getKey();
                                    String value = strinlttringEntry.getValue();
                                    stringBuilder.append("                       <option value=\"" + key + "\">" + value + "</option>\n");
                                }
                                stringBuilder.append(
                                        "                   </select>\n" +
                                                "               </span>\n"
                                );
                            } else if (jdbcColumnModel.isIs_textarea()) {
                                stringBuilder.append(
                                        "                <textarea type=\"text\" class=\"textarea  radius\"  id=\"" + column_name + "\" name=\"" + column_name + "\"></textarea>\n"
                                );
                            } else {
                                stringBuilder.append(
                                        "                <input type=\"text\" class=\"input-text radius\"  id=\"" + column_name + "\" name=\"" + column_name + "\">\n"
                                );
                            }
                            break;
                        case DATETIME:
                            stringBuilder.append(
                                    "                <input type=\"text\"\n" +
                                            "                       onfocus=\"WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})\"\n" +
                                            "                       id=\"" + column_name + "\" name=\"" + column_name + "\" class=\"input-text  radius Wdate\">\n"
                            );
                            break;
                        case LONGTEXT:
                            stringBuilder.append(
                                    "                <input type=\"text\" class=\"input-text radius\"  id=\"" + column_name + "\" name=\"" + column_name + "\">\n"
                            );
                            break;
                        case INT:
                        case DOUBLE:
                            stringBuilder.append(
                                    "                <input type=\"number\" class=\"input-text radius\"  id=\"" + column_name + "\" name=\"" + column_name + "\">\n"
                            );
                            break;
                    }
                    stringBuilder.append("                        </td>");
                    ++i;
                    if (i == 2) {
                        i = 0;
                        stringBuilder.append("                    </tr>");

                    }
                }

            }
            stringBuilder.append("                    </tbody>\n" +
                    "                </table>\n" +
                    "            </div>\n" +
                    "        </div>");
        }
        stringBuilder.append(
                "        <div class=\"row cl\">\n" +
                        "            <div class=\"col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2\">\n" +
                        "                <input class=\"btn btn-primary radius\" type=\"submit\" value=\"&nbsp;&nbsp;提交&nbsp;&nbsp;\">\n" +
                        "                <input class=\"btn btn-primary radius\" type=\"button\"  value=\"&nbsp;&nbsp;取消&nbsp;&nbsp;\" onclick=\"javascript: closed()\">" +
                        "            </div>\n" +
                        "        </div>\n");

        return stringBuilder.toString();
    }


    /**
     * 获取指定数据库的所有表名
     *
     * @param connection
     * @param TABLE_SCHEMA
     * @return
     */
    private ArrayList<String> getAllTableName(Connection connection, String TABLE_SCHEMA) {
        ArrayList<String> strinlt = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?");
            preparedStatement.setString(1, TABLE_SCHEMA);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                strinlt.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strinlt;
    }


    /**
     * 得到指定数据库，指定表的，表名，和列名，列类型
     *
     * @param connection
     * @param table_name
     * @param table_schema
     * @return
     */
    private ArrayList<JdbcColumnModel> getAllColumnName(Connection connection, String table_name, String table_schema) {
        ArrayList<JdbcColumnModel> jdbcColumnModels = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT COLUMN_NAME,COLUMN_TYPE,COLUMN_COMMENT FROM INFORMATION_SCHEMA.Columns WHERE table_name=? AND table_schema=?");
            preparedStatement.setString(1, table_name);
            preparedStatement.setString(2, table_schema);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LOGGER.info(resultSet);

                JdbcColumnModel jdbcColumnModel = new JdbcColumnModel();
                if (resultSet.getString("COLUMN_NAME") != null) {
                    jdbcColumnModel.setColumn_name(resultSet.getString("COLUMN_NAME"));
                }
                if (resultSet.getString("COLUMN_TYPE") != null) {
                    String column_type = resultSet.getString("COLUMN_TYPE");
                    if (column_type.lastIndexOf("(") > 0)
                        LOGGER.info(column_type.substring(0, column_type.lastIndexOf("(")));
                    else
                        LOGGER.info(column_type);
                    jdbcColumnModel.setColumn_type(getOneByName(column_type));
                }
                if (resultSet.getString("COLUMN_COMMENT") != null) {
                    jdbcColumnModel.setColumn_comment(resultSet.getString("COLUMN_COMMENT"));
                }
                jdbcColumnModels.add(jdbcColumnModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jdbcColumnModels;
    }

    private String getTableNameForJava(String tableName) {
        String[] split = tableName.split("_");
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            String sp = split[i];
            str.append(sp.substring(0, 1).toUpperCase()).append(sp.substring(1));
        }
        return str.toString();
    }

    /**
     * 获取JDBC链接
     *
     * @return
     */
    private Connection initCollection(String url, String username, String password) {
        Connection con = null;
        try {
            //加载MySql的驱动类
            Class.forName(DRIVER);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    ///////////////////////////////////////////////////

    private String PACKAGE_NAME;//项目包名
    private String TABLE_NAME;//表名
    private String ANNOTATION_NAME;//表名对应中文
    private String TABLE_ChName = ANNOTATION_NAME + "管理";//页面名称
    private String MENU_PARENT_NAME;//父级菜单名称

    private String CANONICAL_PATH;//获取项目路径

    private String TABLE_SCHEMA;//数据库名

    //java 文件
    private String TABLE_NAME_JAVA;


    private String MODEL_NAME_JAVA;
    private String MODEL_NAME_JAVA_FILE;


    private String DAO_NAME_JAVA;
    private String DAO_NAME_JAVA_FILE;


    private String SERVICE_NAME_JAVA;
    private String SERVICE_NAME_JAVA_FILE;

    private String SERVICE_IMPL_NAME_JAVA;
    private String SERVICE_IMPL_NAME_JAVA_FILE;

    private String CONTROLLER_NAME_JAVA;
    private String CONTROLLER_NAME_JAVA_FILE;

    //xml
    private String MAPPER_NAME_XML;
    private String MAPPER_NAME_XML_FILE;

    //html
    private String HTML_ADD;
    private String HTML_ADD_FILE;

    private String HTML_EDIT;
    private String HTML_EDIT_FILE;

    private String HTML_LIST;
    private String HTML_LIST_FILE;

    private String HTML_ADD_BY_EXCEL;
    private String HTML_ADD_BY_EXCEL_FILE;

    // path
    private String TABLE_NAME_URL;
    private String BASE_PACKAGE_NAME;
    private String PACKAGE_DAO_PATH;
    private String JAVA_PATH;
    private String MODEL_PATH;
    private String DAO_PATH;
    private String CONTROLLER_PATH;
    private String SERVICE_PATH;
    private String SERVICE_IMPL_PATH;
    private String MAPPER_RESULT_MAP_TYPE;

    //url &view
    private String DATA_URL_selectListByPageHelper;
    private String DATA_URL_delete;
    private String DATA_URL_update;
    private String DATA_URL_insert;
    private String DATA_URL_selectById;
    private String DATA_URL_insertByExcel;
    private String DATA_URL_downloadTemplate;

    private String VIEW_PATH_ADD;
    private String VIEW_PATH_EDIT;
    private String VIEW_PATH_IMPORT;

    //reources
    private String RESOURCE_PATH;
    private String RESOURCE_MAPPER_PATH;

    private String RESOURCE_HTML_PATH;


    //数据库相关
    private String DRIVER;

    private String url;
    private String username;
    private String password;

    //实例对象
    private String SERVICE_Instance;

    public void initField() {
        //根路径
        CANONICAL_PATH = System.getProperty("user.dir");
//        TABLE_SCHEMA = CANONICAL_PATH.substring(CANONICAL_PATH.lastIndexOf("\\") + 1).toLowerCase();
        TABLE_SCHEMA = "g_community";
        //java 文件
        TABLE_NAME_JAVA = getTableNameForJava(TABLE_NAME);
        MODEL_NAME_JAVA = TABLE_NAME_JAVA + "Model";
        MODEL_NAME_JAVA_FILE = MODEL_NAME_JAVA + ".java";
        DAO_NAME_JAVA = TABLE_NAME_JAVA + "Dao";
        DAO_NAME_JAVA_FILE = DAO_NAME_JAVA + ".java";
        SERVICE_NAME_JAVA = TABLE_NAME_JAVA + "Service";
        SERVICE_NAME_JAVA_FILE = SERVICE_NAME_JAVA + ".java";
        SERVICE_IMPL_NAME_JAVA = SERVICE_NAME_JAVA + "Impl";
        SERVICE_IMPL_NAME_JAVA_FILE = SERVICE_IMPL_NAME_JAVA + ".java";
        CONTROLLER_NAME_JAVA = TABLE_NAME_JAVA + "Controller";
        CONTROLLER_NAME_JAVA_FILE = TABLE_NAME_JAVA + "Controller.java";
        //xml
        MAPPER_NAME_XML = TABLE_NAME_JAVA + "Mapper";
        MAPPER_NAME_XML_FILE = MAPPER_NAME_XML + ".xml";

        //html
        HTML_ADD = TABLE_NAME_JAVA + "Add";
        HTML_ADD_FILE = HTML_ADD + ".html";

        HTML_EDIT = TABLE_NAME_JAVA + "Edit";
        HTML_EDIT_FILE = HTML_EDIT + ".html";

      /*  HTML_ADD_BY_EXCEL = TABLE_NAME_JAVA + "AddByExcel";
        HTML_ADD_BY_EXCEL_FILE = HTML_ADD_BY_EXCEL + ".html";*/

        HTML_LIST = TABLE_NAME_JAVA + "List";
        HTML_LIST_FILE = HTML_LIST + ".html";

        // path
        TABLE_NAME_URL = TABLE_NAME_JAVA;
        BASE_PACKAGE_NAME = "com.lt.body." + PACKAGE_NAME;
        PACKAGE_DAO_PATH = BASE_PACKAGE_NAME + ".dao";
        JAVA_PATH = CANONICAL_PATH + "/src/main/java/com/community/body/" + PACKAGE_NAME;
        MODEL_PATH = JAVA_PATH + "/model";
        DAO_PATH = JAVA_PATH + "/dao";
        CONTROLLER_PATH = JAVA_PATH + "/controller";
        SERVICE_PATH = JAVA_PATH + "/service";
        SERVICE_IMPL_PATH = JAVA_PATH + "/service/impl";
        MAPPER_RESULT_MAP_TYPE = BASE_PACKAGE_NAME + ".model." + MODEL_NAME_JAVA;

        //url &view
        DATA_URL_selectListByPageHelper = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/selectListByPageHelper";
        DATA_URL_delete = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/deleteById";
        DATA_URL_update = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/updateById";
        DATA_URL_insert = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/insert";
        DATA_URL_selectById = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/selectById";
        // DATA_URL_insertByExcel = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/insertMoreByExcel";
        // DATA_URL_downloadTemplate = "/" + PACKAGE_NAME + "/" + TABLE_NAME_URL + "/downloadTemplate";
        VIEW_PATH_ADD = "/html/" + TABLE_NAME_JAVA + "/" + HTML_ADD_FILE;
        VIEW_PATH_EDIT = "/html/" + TABLE_NAME_JAVA + "/" + HTML_EDIT_FILE;
        //VIEW_PATH_IMPORT = "/static/html/base_import_excel.html";

        //reources
        RESOURCE_PATH = CANONICAL_PATH + "/src/main/resources/";
        RESOURCE_MAPPER_PATH = RESOURCE_PATH + "mapper";
        RESOURCE_HTML_PATH = RESOURCE_PATH + "static/html/" + TABLE_NAME_JAVA;


        //数据库相关
        DRIVER = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/" + TABLE_SCHEMA + "?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        username = "root";
        password = "root";

        //实例对象
        SERVICE_Instance = SERVICE_NAME_JAVA.substring(0, 1).toLowerCase() + SERVICE_NAME_JAVA.substring(1);

        TABLE_ChName = ANNOTATION_NAME + "管理";


        LOGGER.info(TABLE_ChName);
        LOGGER.info(HTML_LIST_FILE);


    }
}

