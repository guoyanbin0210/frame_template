package com.gyb.ainit;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.gyb.ainit.JdbcColumnModelType.*;

public class JdbcDone {
    private String PACKAGE_NAME = "business";//项目包名
    private String TABLE_NAME =  "g_test";//表名
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
    //@org.testng.annotations.Test
    @org.junit.Test
    public void done() throws IOException, SQLException {
        //ArrayList<JdbcColumnModel> init = getJdbcColumnModels();
        //ArrayList<JdbcColumnModel> init = getJdbcColumnModels();
        ArrayList<JdbcColumnModel> init = new ArrayList<>();
        init.add(new JdbcColumnModel("id", VARCHAR, "64", "id--"));
        init.add(new JdbcColumnModel("userId", VARCHAR, "255", "userId--微信就是openId"));
        init.add(new JdbcColumnModel("userName", VARCHAR, "255", "用户名"));
        init.add(new JdbcColumnModel("wxUserName", VARCHAR, "255", "用户名"));
        init.add(new JdbcColumnModel("cardId", VARCHAR, "255", "身份证号"));
        init.add(new JdbcColumnModel("phone", VARCHAR, "255", "手机号"));
        init.add(new JdbcColumnModel("headImg", VARCHAR, "255", "头像"));
        init.add(new JdbcColumnModel("cardUrlA", VARCHAR, "255", "身份证A"));
        init.add(new JdbcColumnModel("cardUrlB", VARCHAR, "255", "身份证B"));
        init.add(new JdbcColumnModel("userType", VARCHAR, "255", "用户类型"));
        init.add(new JdbcColumnModel("verifyStatus", VARCHAR, "4", "审核状态 012"));
        init.add(new JdbcColumnModel("audit_cause", VARCHAR, "255", "审核原因"));
        init.add(new JdbcColumnModel("isAgreeTip", VARCHAR, "4", "是否同意提示信息"));
        init.add(new JdbcColumnModel("surplusMoney", VARCHAR, "1", "账户余额"));
        init.add(new JdbcColumnModel("status", VARCHAR, "4", "有效状态0默认 1有效 2无效"));

        init.add(new JdbcColumnModel("Spare1", VARCHAR, "255", "备用1"));
        init.add(new JdbcColumnModel("Spare2", VARCHAR, "255", "备用2"));
        init.add(new JdbcColumnModel("Spare3", VARCHAR, "255", "备用3"));

        init.add(new JdbcColumnModel("create_time", DATETIME, "1", "创建时间"));
        init.add(new JdbcColumnModel("create_by", VARCHAR, "64", "创建人"));
        init.add(new JdbcColumnModel("modify_by", VARCHAR, "64", "修改人"));
        init.add(new JdbcColumnModel("modify_time", DATETIME, "1", "修改时间"));

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
