package com.lt.body.user.model;

import com.lt.base.model.BaseModel;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with GaoShan.
 * Description:用户信息
 * Create Time: 2019-05-10 03:31
 */
@Component
public class UserMainModel extends BaseModel {

    /**
     * 用户名
     */
    @GsExcelProperty(index = 0, description = "用户名")
    private String user_name;

    /**
     * 手机号
     */
    @GsExcelProperty(index = 1, description = "手机号")
    private String user_phone;

    /**
     * 密码
     */
    @GsExcelProperty(index = 2, description = "密码")
    private String password;

    /**
     * 验证码
     */
    @GsExcelProperty(index = 3, description = "验证码")
    private String check_code;

    /**
     * 验证码过期时间
     */
    @GsExcelProperty(index = 4, description = "验证码过期时间")
    private Date check_code_out;

    /**
     * 详情，暂时没有用上
     */
    private String user_details_id;

    /**
     * 用户头像
     */
    private String user_head_img;

    private String qq_openid;
    private String wx_openid;
    private String wb_openid;

    private String wx_unionid;
    private String wx_app_openid;




    private Integer user_curr_integral;//当前积分
    private Integer user_his_integral;//历史总积分


    /**
     * 用户职业
     */
    private String user_job_type_id;
    private String user_job_type_name;

    public String getWx_unionid() {
        return wx_unionid;
    }

    public void setWx_unionid(String wx_unionid) {
        this.wx_unionid = wx_unionid;
    }

    public String getWx_app_openid() {
        return wx_app_openid;
    }

    public void setWx_app_openid(String wx_app_openid) {
        this.wx_app_openid = wx_app_openid;
    }

    public String getUser_job_type_id() {
        return user_job_type_id;
    }

    public void setUser_job_type_id(String user_job_type_id) {
        this.user_job_type_id = user_job_type_id;
    }

    public String getUser_job_type_name() {
        return user_job_type_name;
    }

    public void setUser_job_type_name(String user_job_type_name) {
        this.user_job_type_name = user_job_type_name;
    }

    public Integer getUser_curr_integral() {
        return user_curr_integral;
    }

    public void setUser_curr_integral(Integer user_curr_integral) {
        this.user_curr_integral = user_curr_integral;
    }

    public Integer getUser_his_integral() {
        return user_his_integral;
    }

    public void setUser_his_integral(Integer user_his_integral) {
        this.user_his_integral = user_his_integral;
    }

    public String getQq_openid() {
        return qq_openid;
    }

    public void setQq_openid(String qq_openid) {
        this.qq_openid = qq_openid;
    }

    public String getWx_openid() {
        return wx_openid;
    }

    public void setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
    }

    public String getWb_openid() {
        return wb_openid;
    }

    public void setWb_openid(String wb_openid) {
        this.wb_openid = wb_openid;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public void setUser_head_img(String user_head_img) {
        this.user_head_img = user_head_img;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code_out(Date check_code_out) {
        this.check_code_out = check_code_out;
    }

    public Date getCheck_code_out() {
        return check_code_out;
    }

    public void setUser_details_id(String user_details_id) {
        this.user_details_id = user_details_id;
    }

    public String getUser_details_id() {
        return user_details_id;
    }


}
