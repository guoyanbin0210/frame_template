package com.lt.body.aboutUs.model;
import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class AboutUsModel extends BaseModel{
    //菜单id:paretn_id,类型：字符串

    //继承菜单id:menu_id,类型：字符串

    //标题:us_title,类型：字符串

    //新闻内容:us_contnt,类型：字符串

    /**
    * 菜单id
    */
    private String parent_id;

    /**
    * 继承菜单id
    */
    private String menu_id;

    /**
    * 标题
    */
    private String us_title;

    /**
    * 新闻内容
    */
    private String us_contnt;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setMenu_id(String menu_id){        this.menu_id=menu_id;    }
 
    public String getMenu_id(){        return menu_id;    }
 
    public void setUs_title(String us_title){        this.us_title=us_title;    }
 
    public String getUs_title(){        return us_title;    }
 
    public void setUs_contnt(String us_contnt){        this.us_contnt=us_contnt;    }
 
    public String getUs_contnt(){        return us_contnt;    }
 
}
