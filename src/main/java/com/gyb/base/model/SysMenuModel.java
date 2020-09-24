package com.gyb.base.model;

import com.gyb.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SysMenuModel extends BaseModel {
    /**
     * 代码
     */
    @GsExcelProperty(index = 0, description = "代码")
    private String menu_code;

    /**
     * 名称
     */
    @GsExcelProperty(index = 1, description = "名称")
    private String menu_name;

    /**
     * url地址
     */
    @GsExcelProperty(index = 2, description = "url地址")
    private String menu_url;

    /**
     * 父级id
     */
    @GsExcelProperty(index = 3, description = "父级id")
    private String menu_parent_id;

    /**
     * 级别
     */
    @GsExcelProperty(index = 4, description = "级别")
    private Integer menu_level;

    /**
     * 描述
     */
    @GsExcelProperty(index = 5, description = "描述")
    private String menu_des;

    /**
     * 类型
     */
    @GsExcelProperty(index = 6, description = "类型")
    private String menu_type;

    private ArrayList<SysMenuModel> children;

    public ArrayList<SysMenuModel> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SysMenuModel> children) {
        this.children = children;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_parent_id(String menu_parent_id) {
        this.menu_parent_id = menu_parent_id;
    }

    public String getMenu_parent_id() {
        return menu_parent_id;
    }

    public void setMenu_level(Integer menu_level) {
        this.menu_level = menu_level;
    }

    public Integer getMenu_level() {
        return menu_level;
    }

    public void setMenu_des(String menu_des) {
        this.menu_des = menu_des;
    }

    public String getMenu_des() {
        return menu_des;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getMenu_type() {
        return menu_type;
    }

}
