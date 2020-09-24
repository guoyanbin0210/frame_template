package com.gyb.base.model;

import com.gyb.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysRoleModel extends BaseModel {

    /**
     * 编码
     */
    @GsExcelProperty(index = 0, description = "编码")
    private String role_code;

    /**
     * 名称
     */
    @GsExcelProperty(index = 1, description = "名称")
    private String role_name;

    /**
     * 类型
     */
    @GsExcelProperty(index = 2, description = "类型")
    private Integer role_type;

    /**
     * 描述
     */
    @GsExcelProperty(index = 3, description = "描述")
    private String role_des;

    @GsExcelProperty(index = 4, description = "绑定菜单的id")
    private String role_band_menu_id;

    /**
     * 包含的权限
     */
    private List<SysPermissionModel> permissions;

    public String getRole_band_menu_id() {
        return role_band_menu_id;
    }

    public void setRole_band_menu_id(String role_band_menu_id) {
        this.role_band_menu_id = role_band_menu_id;
    }

    public List<SysPermissionModel> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermissionModel> permissions) {
        this.permissions = permissions;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_type(Integer role_type) {
        this.role_type = role_type;
    }

    public Integer getRole_type() {
        return role_type;
    }

    public void setRole_des(String role_des) {
        this.role_des = role_des;
    }

    public String getRole_des() {
        return role_des;
    }

}
