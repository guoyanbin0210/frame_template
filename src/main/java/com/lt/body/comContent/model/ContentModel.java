package com.lt.body.comContent.model;
import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * Created with GaoShan.
 * Description:文章管理
 * Create Time: 2020-02-28 03:27
 */
@Component
public class ContentModel extends BaseModel{
    //菜单id:tb_menu_id,类型：字符串

    //继承菜单id:tb_parent_id,类型：字符串

    //标题:tb_title,类型：字符串

    //简介:tb_about,类型：字符串

    //标题照片链接:tb_title_url,类型：字符串

    //文本内容:tb_content,类型：字符串

    /**
    * 菜单id
    */
    private String tb_menu_id;

    /**
    * 继承菜单id
    */
    private String tb_parent_id;

    /**
    * 标题
    */
    private String tb_title;

    /**
    * 简介
    */
    private String tb_about;

    /**
    * 标题照片链接
    */
    private String tb_title_url;

    /**
    * 文本内容
    */
    private String tb_content;

    /**
     * 祖籍id
     */
    private String parent_id;


    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setTb_menu_id(String tb_menu_id){        this.tb_menu_id=tb_menu_id;    }
 
    public String getTb_menu_id(){        return tb_menu_id;    }
 
    public void setTb_parent_id(String tb_parent_id){        this.tb_parent_id=tb_parent_id;    }
 
    public String getTb_parent_id(){        return tb_parent_id;    }
 
    public void setTb_title(String tb_title){        this.tb_title=tb_title;    }
 
    public String getTb_title(){        return tb_title;    }
 
    public void setTb_about(String tb_about){        this.tb_about=tb_about;    }
 
    public String getTb_about(){        return tb_about;    }
 
    public void setTb_title_url(String tb_title_url){        this.tb_title_url=tb_title_url;    }
 
    public String getTb_title_url(){        return tb_title_url;    }
 
    public void setTb_content(String tb_content){        this.tb_content=tb_content;    }
 
    public String getTb_content(){        return tb_content;    }
 
}
