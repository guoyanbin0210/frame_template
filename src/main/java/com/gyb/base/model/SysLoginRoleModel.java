package com.gyb.base.model;

import com.gyb.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;
@Component
public class SysLoginRoleModel extends BaseModel{
    /**
    * 登陆id
    */
    @GsExcelProperty(index = 0, description = "登陆id")
    private String login_id;

    /**
    * 角色id
    */
    @GsExcelProperty(index = 1, description = "角色id")
    private String role_id;

    public void setLogin_id(String login_id){        this.login_id=login_id;    }
 
    public String getLogin_id(){        return login_id;    }
 
    public void setRole_id(String role_id){        this.role_id=role_id;    }
 
    public String getRole_id(){        return role_id;    }
 
}
