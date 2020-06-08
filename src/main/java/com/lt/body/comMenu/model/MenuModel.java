package com.lt.body.comMenu.model;
import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class MenuModel extends BaseModel{

    /**
    * 菜单名称
    */
    private String menu_name;

    /**
    * 父级id
    */
    private String menu_parent_id;

    /**
    * 排序
    */
    private Integer menu_level;

    /**
    * 备注
    */
    private String menu_remark;

    /**
     * 排序
     */
    private Integer menu_sort;

    private String menu_url;

    public Integer getMenu_sort() {
        return menu_sort;
    }

    public void setMenu_sort(Integer menu_sort) {
        this.menu_sort = menu_sort;
    }

    public void setMenu_name(String menu_name){        this.menu_name=menu_name;    }
 
    public String getMenu_name(){        return menu_name;    }
 
    public void setMenu_parent_id(String menu_parent_id){        this.menu_parent_id=menu_parent_id;    }
 
    public String getMenu_parent_id(){        return menu_parent_id;    }
 
    public void setMenu_level(Integer menu_level){        this.menu_level=menu_level;    }
 
    public Integer getMenu_level(){        return menu_level;    }
 
    public void setMenu_remark(String menu_remark){        this.menu_remark=menu_remark;    }
 
    public String getMenu_remark(){        return menu_remark;    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }
}
