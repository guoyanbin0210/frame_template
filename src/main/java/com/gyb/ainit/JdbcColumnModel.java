package com.gyb.ainit;

import java.util.LinkedHashMap;

public class JdbcColumnModel {
    private String column_name,//userName
            column_comment,//用户名
            column_size;//字段长度
    private JdbcColumnModelType column_type;

    private LinkedHashMap<String, String> dataList;
    private boolean is_textarea;


    JdbcColumnModel() {

    }

    JdbcColumnModel(String column_name, JdbcColumnModelType column_type, String column_size, String column_comment) {
        this.column_name = column_name;
        this.column_comment = column_comment;
        this.column_size = column_size;
        this.column_type = column_type;
    }


    public JdbcColumnModel(String column_name, JdbcColumnModelType column_type, String column_size, String column_comment, LinkedHashMap<String, String> dataList) {
        this.column_name = column_name;
        this.column_comment = column_comment;
        this.column_size = column_size;
        this.column_type = column_type;
        this.dataList = dataList;
    }




    public JdbcColumnModel(String column_name, JdbcColumnModelType column_type, String column_size, String column_comment, boolean is_textarea) {
        this.column_name = column_name;
        this.column_comment = column_comment;
        this.column_size = column_size;
        this.column_type = column_type;
        this.is_textarea = is_textarea;
    }

    public boolean isIs_textarea() {
        return is_textarea;
    }

    public void setIs_textarea(boolean is_textarea) {
        this.is_textarea = is_textarea;
    }

    @Override
    public String toString() {

        return "JdbcColumnModel{" + "column_name='" + column_name + '\'' + ", column_type='" + column_type.getName_sql() + '\'' + ", column_comment='" + column_comment + '\'' + ", column_size='" + column_size + '\'' + '}';
    }

    public LinkedHashMap<String, String> getDataList() {
        return dataList;
    }

    public void setDataList(LinkedHashMap<String, String> dataList) {
        this.dataList = dataList;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_comment() {
        return column_comment;
    }

    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

    public String getColumn_size() {
        return column_size;
    }

    public void setColumn_size(String column_size) {
        this.column_size = column_size;
    }

    public JdbcColumnModelType getColumn_type() {
        return column_type;
    }

    public void setColumn_type(JdbcColumnModelType column_type) {
        this.column_type = column_type;
    }
}
