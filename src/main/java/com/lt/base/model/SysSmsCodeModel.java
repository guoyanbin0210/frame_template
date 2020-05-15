package com.lt.base.model;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * Created with GaoShan.
 * Description:
 * Create Time: 2019-01-04 11:22
 */
@Component
public class SysSmsCodeModel extends BaseModel{

    /**
    * 手机号
    */
    @GsExcelProperty(index = 0, description = "手机号")
    private String ssc_phone;

    /**
    * 验证码
    */
    @GsExcelProperty(index = 1, description = "验证码")
    private String ssl_code;

    /**
    * 过期时间
    */
    @GsExcelProperty(index = 2, description = "过期时间")
    private Date ssl_out_time;

    public void setSsc_phone(String ssc_phone){        this.ssc_phone=ssc_phone;    }
 
    public String getSsc_phone(){        return ssc_phone;    }
 
    public void setSsl_code(String ssl_code){        this.ssl_code=ssl_code;    }
 
    public String getSsl_code(){        return ssl_code;    }
 
    public void setSsl_out_time(Date ssl_out_time){        this.ssl_out_time=ssl_out_time;    }
 
    public Date getSsl_out_time(){        return ssl_out_time;    }
 
}
