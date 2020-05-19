package com.lt.base.model;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

@Component
public class SysSmsModelModel extends BaseModel{

    /**
    * 签名名称
    */
    @GsExcelProperty(index = 0, description = "签名名称")
    private String ssm_signature_name;

    /**
    * 模板类型
    */
    @GsExcelProperty(index = 1, description = "模板类型")
    private String ssm_type;

    /**
    * 模版CODE
    */
    @GsExcelProperty(index = 2, description = "模版CODE")
    private String ssm_code;

    /**
    * 模版名称
    */
    @GsExcelProperty(index = 3, description = "模版名称")
    private String ssm_name;

    /**
    * 模版内容
    */
    @GsExcelProperty(index = 4, description = "模版内容")
    private String ssm_content;

    /**
    * 参数
    */
    @GsExcelProperty(index = 5, description = "参数")
    private String ssm_parameters;

    /**
     * 展示参数
     */
    @GsExcelProperty(index = 6, description = "展示参数")
    private String ssm_show_params;

    public String getSsm_show_params() {
        return ssm_show_params;
    }

    public void setSsm_show_params(String ssm_show_params) {
        this.ssm_show_params = ssm_show_params;
    }

    public void setSsm_signature_name(String ssm_signature_name){        this.ssm_signature_name=ssm_signature_name;    }
 
    public String getSsm_signature_name(){        return ssm_signature_name;    }
 
    public void setSsm_type(String ssm_type){        this.ssm_type=ssm_type;    }
 
    public String getSsm_type(){        return ssm_type;    }
 
    public void setSsm_code(String ssm_code){        this.ssm_code=ssm_code;    }
 
    public String getSsm_code(){        return ssm_code;    }
 
    public void setSsm_name(String ssm_name){        this.ssm_name=ssm_name;    }
 
    public String getSsm_name(){        return ssm_name;    }
 
    public void setSsm_content(String ssm_content){        this.ssm_content=ssm_content;    }
 
    public String getSsm_content(){        return ssm_content;    }
 
    public void setSsm_parameters(String ssm_parameters){        this.ssm_parameters=ssm_parameters;    }
 
    public String getSsm_parameters(){        return ssm_parameters;    }
 
}
