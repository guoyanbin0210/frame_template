package com.lt.base.model;

import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

/**
 * Created with GaoShan.
 * Description:
 * Create Time: 2019-01-02 01:37
 */
@Component
public class SysSmsLogModel extends BaseModel {

    /**
     * 短信类型
     */
    @GsExcelProperty(index = 0, description = "短信类型")
    private Integer ssl_type;

    /**
     * 发送内容
     */
    @GsExcelProperty(index = 1, description = "发送内容")
    private String ssl_send_body;

    /**
     * 接收者
     */
    @GsExcelProperty(index = 2, description = "接收者")
    private String ssl_receive;

    /**
     * 短信长度
     */
    @GsExcelProperty(index = 3, description = "短信长度")
    private Integer ssl_body_len;

    /**
     * 单条短信计数
     */
    @GsExcelProperty(index = 4, description = "单条短信计数")
    private Integer ssl_count;

    /**
     * 短信是否发送成功
     */
    @GsExcelProperty(index = 5, description = "短信是否发送成功")
    private Integer ssl_is_success;

    /**
     * 请求返回信息
     */
    @GsExcelProperty(index = 6, description = "请求返回信息")
    private String ssl_return_msg;

    @GsExcelProperty(index = 7, description = "模板代码")
    private String ssl_model_code;


    public String getSsl_model_code() {
        return ssl_model_code;
    }

    public void setSsl_model_code(String ssl_model_code) {
        this.ssl_model_code = ssl_model_code;
    }

    public void setSsl_type(Integer ssl_type) {
        this.ssl_type = ssl_type;
    }

    public Integer getSsl_type() {
        return ssl_type;
    }

    public void setSsl_send_body(String ssl_send_body) {
        this.ssl_send_body = ssl_send_body;
    }

    public String getSsl_send_body() {
        return ssl_send_body;
    }

    public void setSsl_receive(String ssl_receive) {
        this.ssl_receive = ssl_receive;
    }

    public String getSsl_receive() {
        return ssl_receive;
    }

    public void setSsl_body_len(Integer ssl_body_len) {
        this.ssl_body_len = ssl_body_len;
    }

    public Integer getSsl_body_len() {
        return ssl_body_len;
    }

    public void setSsl_count(Integer ssl_count) {
        this.ssl_count = ssl_count;
    }

    public Integer getSsl_count() {
        return ssl_count;
    }

    public void setSsl_is_success(Integer ssl_is_success) {
        this.ssl_is_success = ssl_is_success;
    }

    public Integer getSsl_is_success() {
        return ssl_is_success;
    }

    public void setSsl_return_msg(String ssl_return_msg) {
        this.ssl_return_msg = ssl_return_msg;
    }

    public String getSsl_return_msg() {
        return ssl_return_msg;
    }

}
