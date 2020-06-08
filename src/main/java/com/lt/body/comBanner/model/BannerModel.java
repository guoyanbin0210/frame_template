package com.lt.body.comBanner.model;
import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class BannerModel extends BaseModel{

    /**
    * 照片链接
    */
    private String banner_url;

    /**
    * 对应链接id
    */
    private String parent_id;

    /**
    * 名称
    */
    private String menu_id;

    /**
    * 排序
    */
    private Integer banner_sort;

    /**
    * 分类0：banner图 1：图标logo
    */
    private Integer banner_type;

    public void setBanner_url(String banner_url){        this.banner_url=banner_url;    }
 
    public String getBanner_url(){        return banner_url;    }
 
    public void setParent_id(String parent_id){        this.parent_id=parent_id;    }
 
    public String getParent_id(){        return parent_id;    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public void setBanner_sort(Integer banner_sort){        this.banner_sort=banner_sort;    }
 
    public Integer getBanner_sort(){        return banner_sort;    }
 
    public void setBanner_type(Integer banner_type){        this.banner_type=banner_type;    }
 
    public Integer getBanner_type(){        return banner_type;    }
 
}
