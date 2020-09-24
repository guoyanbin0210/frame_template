package com.gyb.body.appManager.model;
import com.gyb.base.model.BaseModel;
import org.springframework.stereotype.Component;

@Component
public class AppManagerModel extends BaseModel{
    //包名:package_name,类型：字符串

    //应用名称:label,类型：字符串

    //图标:icon_url,类型：字符串

    //版本名称:version_name,类型：字符串

    //版本号:version_code,类型：整型

    //最低android版本:min_sdk_version,类型：字符串

    //下载地址:down_url,类型：字符串

    //二维码:a_qr_code,类型：字符串

    //下载次数:down_num,类型：整型

    /**
    * 包名
    */
    private String package_name;

    /**
    * 应用名称
    */
    private String label;

    /**
    * 图标
    */
    private String icon_url;

    /**
    * 版本名称
    */
    private String version_name;

    /**
    * 版本号
    */
    private Integer version_code;

    /**
    * 最低android版本
    */
    private String min_sdk_version;

    /**
    * 下载地址
    */
    private String down_url;

    /**
    * 二维码
    */
    private String a_qr_code;

    /**
    * 下载次数
    */
    private Integer down_num;

    /**
     * 更新记录
     */
    private String record;

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setPackage_name(String package_name){        this.package_name=package_name;    }
 
    public String getPackage_name(){        return package_name;    }
 
    public void setLabel(String label){        this.label=label;    }
 
    public String getLabel(){        return label;    }
 
    public void setIcon_url(String icon_url){        this.icon_url=icon_url;    }
 
    public String getIcon_url(){        return icon_url;    }
 
    public void setVersion_name(String version_name){        this.version_name=version_name;    }
 
    public String getVersion_name(){        return version_name;    }
 
    public void setVersion_code(Integer version_code){        this.version_code=version_code;    }
 
    public Integer getVersion_code(){        return version_code;    }
 
    public void setMin_sdk_version(String min_sdk_version){        this.min_sdk_version=min_sdk_version;    }
 
    public String getMin_sdk_version(){        return min_sdk_version;    }
 
    public void setDown_url(String down_url){        this.down_url=down_url;    }
 
    public String getDown_url(){        return down_url;    }
 
    public void setA_qr_code(String a_qr_code){        this.a_qr_code=a_qr_code;    }
 
    public String getA_qr_code(){        return a_qr_code;    }
 
    public void setDown_num(Integer down_num){        this.down_num=down_num;    }
 
    public Integer getDown_num(){        return down_num;    }
 
}
