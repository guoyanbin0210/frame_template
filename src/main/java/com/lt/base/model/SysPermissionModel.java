package com.lt.base.model;

import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

@Component
public class SysPermissionModel extends BaseModel {
    /**
     * 编码
     */
    @GsExcelProperty(index = 0, description = "编码")
    private String permission_code;

    /**
     * 名称
     */
    @GsExcelProperty(index = 1, description = "名称")
    private String permission_name;

    /**
     * url
     */
    @GsExcelProperty(index = 2, description = "url")
    private String permission_url;

    /**
     * 类型
     */
    @GsExcelProperty(index = 3, description = "类型")
    private Integer permission_type;

    /**
     * 描述
     */
    @GsExcelProperty(index = 4, description = "描述")
    private String permission_des;

    @GsExcelProperty(index = 5, description = "绑定菜单的id")
    private String permission_band_menu_id;


    /**
     * 用于建立树型结构
     */
    private String parent_id;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPermission_band_menu_id() {
        return permission_band_menu_id;
    }

    public void setPermission_band_menu_id(String permission_band_menu_id) {
        this.permission_band_menu_id = permission_band_menu_id;
    }

    public void setPermission_code(String permission_code) {
        this.permission_code = permission_code;
    }

    public String getPermission_code() {
        return permission_code;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_url(String permission_url) {
        this.permission_url = permission_url;
    }

    public String getPermission_url() {
        return permission_url;
    }

    public void setPermission_type(Integer permission_type) {
        this.permission_type = permission_type;
    }

    public Integer getPermission_type() {
        return permission_type;
    }

    public void setPermission_des(String permission_des) {
        this.permission_des = permission_des;
    }

    public String getPermission_des() {
        return permission_des;
    }

}
