package com.lt.ainit;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.lt.ainit.JdbcColumnModelType.*;

public class JdbcDone {
    private String PACKAGE_NAME = "business";//项目包名
    private String TABLE_NAME =    "g_order";//表名
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
        //ArrayList<JdbcColumnModel> init = getJdbcColumnModels();
        //ArrayList<JdbcColumnModel> init = getJdbcColumnModels();
        ArrayList<JdbcColumnModel> init = new ArrayList<>();
        init.add(new JdbcColumnModel("id", VARCHAR, "64", "id"));
        init.add(new JdbcColumnModel("userId", VARCHAR, "255", "关联用户ID"));
        init.add(new JdbcColumnModel("orderNo", VARCHAR, "255", "订单号"));
        init.add(new JdbcColumnModel("goodsName", VARCHAR, "255", "商品名称"));
        init.add(new JdbcColumnModel("goodsId", VARCHAR, "255", "商品ID"));
        init.add(new JdbcColumnModel("unitPrice", VARCHAR, "255", "单价"));
        init.add(new JdbcColumnModel("buyCount", VARCHAR, "255", "购买数量"));
        init.add(new JdbcColumnModel("weight", VARCHAR, "255", "重量"));
        init.add(new JdbcColumnModel("amount", VARCHAR, "255", "总价 "));
        init.add(new JdbcColumnModel("payTime", DATETIME, "1", "支付时间"));
        init.add(new JdbcColumnModel("expireTime", DATETIME, "1", "过期时间"));
        init.add(new JdbcColumnModel("isPay", VARCHAR, "255", "是否支付"));
        init.add(new JdbcColumnModel("orderStatus", VARCHAR, "8", "订单状态0 待付款，1已付款，2 未付款失效，3已完成"));
        init.add(new JdbcColumnModel("status", VARCHAR, "8", "有效状态0默认 1有效 2无效"));
        init.add(new JdbcColumnModel("orderType", VARCHAR, "8", "订单类型 1支付，2充值 ，3余额支付"));

        init.add(new JdbcColumnModel("Spare1", VARCHAR, "255", "备用1"));
        init.add(new JdbcColumnModel("Spare2", VARCHAR, "255", "备用2"));
        init.add(new JdbcColumnModel("Spare3", VARCHAR, "255", "备用3"));

        init.add(new JdbcColumnModel("create_time", DATETIME, "1", "创建时间"));
        init.add(new JdbcColumnModel("create_by", VARCHAR, "64", "创建人"));
        init.add(new JdbcColumnModel("modify_by", VARCHAR, "64", "modify_by"));
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
