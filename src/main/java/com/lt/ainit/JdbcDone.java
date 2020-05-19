package com.lt.ainit;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.lt.ainit.JdbcColumnModelType.*;

/**
 * ClassName: JdbcDone
 * Description:
 * date: 2019/4/26 10:30
 *
 * @author GaoShan
 */
public class JdbcDone {
    private String PACKAGE_NAME = "address";//项目包名
    private String TABLE_NAME = "tb_address";//表名
    private String ANNOTATION_NAME = "信息";//表名对应中文
    private String MENU_PARENT_NAME = "信息";//父级菜单名称
    JdbcUtils jdbcUtils = new JdbcUtils(PACKAGE_NAME, TABLE_NAME, ANNOTATION_NAME, MENU_PARENT_NAME);

    /**
     * 初始化数据库信息，包括创建数据库
     */
    @Test
    public void initDataBase() throws SQLException {
        jdbcUtils.initDateInfo();
    }

    @org.junit.Test
    public void done() throws IOException, SQLException {
        ArrayList<JdbcColumnModel> init = getJdbcColumnModels();
        init.add(new JdbcColumnModel("address", VARCHAR, "255", "地址"));
        init.add(new JdbcColumnModel("postal_code", VARCHAR, "255", "邮政编码"));
        init.add(new JdbcColumnModel("contact", VARCHAR, "255", "联系方式"));
        init.add(new JdbcColumnModel("fax", VARCHAR, "255", "传真"));
        init.add(new JdbcColumnModel("email", VARCHAR, "255", "电子邮件"));
        init.add(new JdbcColumnModel("logo_url", VARCHAR, "255", "logo路径"));
        init.add(new JdbcColumnModel("picture_url", VARCHAR, "255", "照片路径"));
        jdbcUtils.doMain(init, true, true, true);
    }

    private ArrayList<JdbcColumnModel> getJdbcColumnModels() {
        ArrayList<JdbcColumnModel> init = new ArrayList<>();
        init.add(new JdbcColumnModel("id", VARCHAR, "64", "id"));
        init.add(new JdbcColumnModel("create_by", VARCHAR, "64", "create_by"));
        init.add(new JdbcColumnModel("create_time", DATETIME, "64", "create_time"));
        init.add(new JdbcColumnModel("modify_by", VARCHAR, "64", "modify_by"));
        init.add(new JdbcColumnModel("modify_time", DATETIME, "64", "modify_time"));
        LinkedHashMap<String, String> dataList = new LinkedHashMap<>();
        dataList.put("0", "否");
        dataList.put("1", "是");

        return init;
    }
}
