package com.gyb.base.model;
import com.gyb.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

@Component
public class SysRolePermissionModel extends BaseModel{

    /**
    * 角色id
    */
    @GsExcelProperty(index = 0, description = "角色id")
    private String role_id;

    /**
    * 权限id
    */
    @GsExcelProperty(index = 1, description = "权限id")
    private String permission_id;

    public void setRole_id(String role_id){        this.role_id=role_id;    }
 
    public String getRole_id(){        return role_id;    }
 
    public void setPermission_id(String permission_id){        this.permission_id=permission_id;    }
 
    public String getPermission_id(){        return permission_id;    }
 
}
