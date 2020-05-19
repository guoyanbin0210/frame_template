package com.lt.base.model;

import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class SysLoginModel extends BaseModel {
    /**
     * 用户名
     */
    @GsExcelProperty(index = 0, description = "用户名")
    private String user_name;

    private String nick_name;

    /**
     * 密码
     */
    @GsExcelProperty(index = 1, description = "密码")
    private String password;

    /**
     * 手机号
     */
    @GsExcelProperty(index = 2, description = "手机号")
    private String user_phone;

    /**
     * 验证码
     */
    @GsExcelProperty(index = 3, description = "验证码")
    private String check_code;

    /**
     * 验证码过期时间
     */
    @GsExcelProperty(index = 4, description = "验证码过期时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date check_code_expire_date;
    /**
     * 是否锁定
     */
    @GsExcelProperty(index = 5, description = "是否锁定")
    private Integer is_locked = 0;

    /**
     * 用户类型 1后台用户 2注册用户
     */
    @GsExcelProperty(index = 6, description = "用户类型")
    private Integer user_type;

    /**
     * 村庄id
     */
    private String village_id;

    /**
     * 包含的角色
     */
    private List<SysRoleModel> roles;

    /**
     * 最后一次登录日志
     */
    private SysLoginLogModel lastLoginLog;


    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public SysLoginLogModel getLastLoginLog() {
        return lastLoginLog;
    }

    public void setLastLoginLog(SysLoginLogModel lastLoginLog) {
        this.lastLoginLog = lastLoginLog;
    }

    public Date getCheck_code_expire_date() {
        return check_code_expire_date;
    }

    public void setCheck_code_expire_date(Date check_code_expire_date) {
        this.check_code_expire_date = check_code_expire_date;
    }

    public List<SysRoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleModel> roles) {
        this.roles = roles;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setIs_locked(Integer is_locked) {
        this.is_locked = is_locked;
    }

    public Integer getIs_locked() {
        return is_locked;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }
}
