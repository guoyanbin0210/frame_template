package com.lt.body.newContent.model;
import com.lt.base.model.BaseModel;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * Created with GaoShan.
 * Description:新闻管理
 * Create Time: 2020-02-28 10:34
 */
@Component
public class NewContentModel extends BaseModel{

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
    private String news_title;

    /**
    * 标题照片
    */
    private String title_url;

    /**
    * 发布日期
    */
    private String publish_date;

    /**
    * 作者
    */
    private String author;

    /**
    * 新闻内容
    */
    private String news_contnt;

    /**
    * 是否推荐到首页 0：是 1：否
    */
    private Integer news_sort;

    private String news_about;

    public String getNews_about() {
        return news_about;
    }

    public void setNews_about(String news_about) {
        this.news_about = news_about;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setMenu_id(String menu_id){        this.menu_id=menu_id;    }
 
    public String getMenu_id(){        return menu_id;    }
 
    public void setNews_title(String news_title){        this.news_title=news_title;    }
 
    public String getNews_title(){        return news_title;    }
 
    public void setTitle_url(String title_url){        this.title_url=title_url;    }
 
    public String getTitle_url(){        return title_url;    }
 
    public void setPublish_date(String publish_date){        this.publish_date=publish_date;    }
 
    public String getPublish_date(){        return publish_date;    }
 
    public void setAuthor(String author){        this.author=author;    }
 
    public String getAuthor(){        return author;    }
 
    public void setNews_contnt(String news_contnt){        this.news_contnt=news_contnt;    }
 
    public String getNews_contnt(){        return news_contnt;    }
 
    public void setNews_sort(Integer news_sort){        this.news_sort=news_sort;    }
 
    public Integer getNews_sort(){        return news_sort;    }
 
}
