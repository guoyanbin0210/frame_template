package com.gyb.body.aboutUs.model;
import com.gyb.base.model.BaseModel;
import org.springframework.stereotype.Component;

@Component
public class AddressModel extends BaseModel{
    //地址:address,类型：字符串

    //邮政编码:postal_code,类型：字符串

    //联系方式:contact,类型：字符串

    //传真:fax,类型：字符串

    //电子邮件:email,类型：字符串

    //logo路径:logo_url,类型：字符串

    //照片路径:picture_url,类型：字符串

    /**
    * 地址
    */
    private String address;

    /**
    * 邮政编码
    */
    private String postal_code;

    /**
    * 联系方式
    */
    private String contact;

    /**
    * 传真
    */
    private String fax;

    /**
    * 电子邮件
    */
    private String email;

    /**
    * logo路径
    */
    private String logo_url;

    /**
    * 照片路径
    */
    private String picture_url;

    public void setAddress(String address){        this.address=address;    }
 
    public String getAddress(){        return address;    }
 
    public void setPostal_code(String postal_code){        this.postal_code=postal_code;    }
 
    public String getPostal_code(){        return postal_code;    }
 
    public void setContact(String contact){        this.contact=contact;    }
 
    public String getContact(){        return contact;    }
 
    public void setFax(String fax){        this.fax=fax;    }
 
    public String getFax(){        return fax;    }
 
    public void setEmail(String email){        this.email=email;    }
 
    public String getEmail(){        return email;    }
 
    public void setLogo_url(String logo_url){        this.logo_url=logo_url;    }
 
    public String getLogo_url(){        return logo_url;    }
 
    public void setPicture_url(String picture_url){        this.picture_url=picture_url;    }
 
    public String getPicture_url(){        return picture_url;    }
 
}
