package com.lt.body.thirdPart.model;
import com.lt.base.model.BaseModel;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

/**
 * Description:服务管理
 * Create Time: 2019-05-17 09:38
 */
@Component
public class OttServicesModel extends BaseModel{
    /**
    * icon
    */
    @GsExcelProperty(index = 0, description = "icon")
    private String ser_icon;

    /**
    * 标签
    */
    @GsExcelProperty(index = 1, description = "标签")
    private String ser_lable;

    /**
    * 版权
    */
    @GsExcelProperty(index = 2, description = "版权")
    private String ser_copyRight;

    /**
    * 服务提供商
    */
    @GsExcelProperty(index = 3, description = "服务提供商")
    private String ser_name;

    /**
    * 热线
    */
    @GsExcelProperty(index = 4, description = "热线")
    private String ser_hotline;

    /**
    * 跳转连接
    */
    @GsExcelProperty(index = 5, description = "跳转连接")
    private String ser_href;

    /**
    * 类型
    */
    @GsExcelProperty(index = 6, description = "类型")
    private String ser_type;

    /**
    * 访问次数
    */
    @GsExcelProperty(index = 7, description = "访问次数")
    private Integer ser_read_num;

    /**
     * 展示类型。 1：跳转， 2 图文
     */
    private Integer ser_show_type;


    public Integer getSer_show_type() {
        return ser_show_type;
    }

    public void setSer_show_type(Integer ser_show_type) {
        this.ser_show_type = ser_show_type;
    }

    public void setSer_icon(String ser_icon){        this.ser_icon=ser_icon;    }
 
    public String getSer_icon(){        return ser_icon;    }
 
    public void setSer_lable(String ser_lable){        this.ser_lable=ser_lable;    }
 
    public String getSer_lable(){        return ser_lable;    }
 
    public void setSer_copyRight(String ser_copyRight){        this.ser_copyRight=ser_copyRight;    }
 
    public String getSer_copyRight(){        return ser_copyRight;    }
 
    public void setSer_name(String ser_name){        this.ser_name=ser_name;    }
 
    public String getSer_name(){        return ser_name;    }
 
    public void setSer_hotline(String ser_hotline){        this.ser_hotline=ser_hotline;    }
 
    public String getSer_hotline(){        return ser_hotline;    }
 
    public void setSer_href(String ser_href){        this.ser_href=ser_href;    }
 
    public String getSer_href(){        return ser_href;    }
 
    public void setSer_type(String ser_type){        this.ser_type=ser_type;    }
 
    public String getSer_type(){        return ser_type;    }
 
    public void setSer_read_num(Integer ser_read_num){        this.ser_read_num=ser_read_num;    }
 
    public Integer getSer_read_num(){        return ser_read_num;    }
 
}
