package com.lt.base.model;

import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SysLoginLogModel extends BaseModel {


    /**
     * 登录id
     */
    @GsExcelProperty(index = 0, description = "登录id")
    private String sll_login_id;


    /**
     * 登录时间
     */
    @GsExcelProperty(index = 1, description = "登录时间")
    private Date sll_login_time;

    /**
     * 登录ip
     */
    @GsExcelProperty(index = 2, description = "登录ip")
    private String sll_login_ip;


    /**
     * 登录次数 用于返回给前端
     */
    private Integer login_count;

    public Integer getLogin_count() {
        return login_count;
    }

    public void setLogin_count(Integer login_count) {
        this.login_count = login_count;
    }

    /**
     * 登录用户名
     */
    private String ssl_login_name;

    public String getSsl_login_name() {
        return ssl_login_name;
    }

    public void setSsl_login_name(String ssl_login_name) {
        this.ssl_login_name = ssl_login_name;
    }

    public void setSll_login_id(String sll_login_id) {
        this.sll_login_id = sll_login_id;
    }

    public String getSll_login_id() {
        return sll_login_id;
    }

    public Date getSll_login_time() {
        return sll_login_time;
    }

    public void setSll_login_time(Date sll_login_time) {
        this.sll_login_time = sll_login_time;
    }

    public void setSll_login_ip(String sll_login_ip) {
        this.sll_login_ip = sll_login_ip;
    }

    public String getSll_login_ip() {
        return sll_login_ip;
    }

}
