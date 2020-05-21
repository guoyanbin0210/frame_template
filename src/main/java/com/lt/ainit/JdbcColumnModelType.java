package com.lt.ainit;

public enum JdbcColumnModelType {
    VARCHAR("varchar","String", 1),
    DATETIME("datetime", "Date",2),
    LONGTEXT("longtext","String", 3),
    INT("int", "Integer",4),
    DOUBLE("double","Double", 5),
    DATE("date","Date",6),
    ;
    private String name_sql;
    private String name_java;
    private int code;
    JdbcColumnModelType(String name_sql, String name_java, int code) {
        this.name_sql = name_sql;
        this.name_java = name_java;
        this.code = code;
    }

    public String getName_java() {
        return name_java;
    }

    public void setName_java(String name_java) {
        this.name_java = name_java;
    }

    public String getName_sql() {
        return name_sql;
    }

    public void setName_sql(String name_sql) {
        this.name_sql = name_sql;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static JdbcColumnModelType getOneByName(String name) {
        for (JdbcColumnModelType value : JdbcColumnModelType.values()) {
            if (value.getName_sql().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }
}
