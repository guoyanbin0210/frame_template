package com.lt.body.aboutUs.model;

import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:账号管理
 */
@Component
public class AccountModel extends BaseModel{
    //类型:tb_type,类型：整型

    //名称:tb_name,类型：字符串

    //是否属于本公司:tb_company,类型：整型

    //状态:tb_status,类型：整型

    //账号:tb_account,类型：字符串

    //密码:tb_password,类型：字符串

    //总管理员:tb_admin,类型：字符串

    //总管理员:tb_operator,类型：字符串

    //管理员手机号:tb_phone,类型：字符串

    //appId:tb_appId,类型：字符串

    //秘钥:tb_secret_key,类型：字符串

    //过期时间:tb_over_time,类型：字符串

    //主体公司:tb_main_company,类型：字符串

    //备注:tb_remark,类型：字符串

    /**
    * 类型
    */
    private Integer tb_type;

    /**
    * 名称
    */
    private String tb_name;

    /**
    * 是否属于本公司
    */
    private Integer tb_company;

    /**
    * 状态
    */
    private Integer tb_status;

    /**
    * 账号
    */
    private String tb_account;

    /**
    * 密码
    */
    private String tb_password;

    /**
    * 总管理员
    */
    private String tb_admin;

    /**
    * 总管理员
    */
    private String tb_operator;

    /**
    * 管理员手机号
    */
    private String tb_phone;

    /**
    * appId
    */
    private String tb_appId;

    /**
    * 秘钥
    */
    private String tb_secret_key;

    /**
    * 过期时间
    */
    private Date tb_over_time;

    /**
    * 主体公司
    */
    private String tb_main_company;

    /**
    * 备注
    */
    private String tb_remark;

    public void setTb_type(Integer tb_type){        this.tb_type=tb_type;    }
 
    public Integer getTb_type(){        return tb_type;    }
 
    public void setTb_name(String tb_name){        this.tb_name=tb_name;    }
 
    public String getTb_name(){        return tb_name;    }
 
    public void setTb_company(Integer tb_company){        this.tb_company=tb_company;    }
 
    public Integer getTb_company(){        return tb_company;    }
 
    public void setTb_status(Integer tb_status){        this.tb_status=tb_status;    }
 
    public Integer getTb_status(){        return tb_status;    }
 
    public void setTb_account(String tb_account){        this.tb_account=tb_account;    }
 
    public String getTb_account(){        return tb_account;    }
 
    public void setTb_password(String tb_password){        this.tb_password=tb_password;    }
 
    public String getTb_password(){        return tb_password;    }
 
    public void setTb_admin(String tb_admin){        this.tb_admin=tb_admin;    }
 
    public String getTb_admin(){        return tb_admin;    }
 
    public void setTb_operator(String tb_operator){        this.tb_operator=tb_operator;    }
 
    public String getTb_operator(){        return tb_operator;    }
 
    public void setTb_phone(String tb_phone){        this.tb_phone=tb_phone;    }
 
    public String getTb_phone(){        return tb_phone;    }
 
    public void setTb_appId(String tb_appId){        this.tb_appId=tb_appId;    }
 
    public String getTb_appId(){        return tb_appId;    }
 
    public void setTb_secret_key(String tb_secret_key){        this.tb_secret_key=tb_secret_key;    }
 
    public String getTb_secret_key(){        return tb_secret_key;    }

    public Date getTb_over_time() {
        return tb_over_time;
    }

    public void setTb_over_time(Date tb_over_time) {
        this.tb_over_time = tb_over_time;
    }

    public void setTb_main_company(String tb_main_company){        this.tb_main_company=tb_main_company;    }
 
    public String getTb_main_company(){        return tb_main_company;    }
 
    public void setTb_remark(String tb_remark){        this.tb_remark=tb_remark;    }
 
    public String getTb_remark(){        return tb_remark;    }
 
}
