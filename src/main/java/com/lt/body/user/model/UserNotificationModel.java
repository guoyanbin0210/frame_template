package com.lt.body.user.model;

import com.lt.base.model.BaseModel;
import com.lt.base.poi.annotation.GsExcelProperty;
import org.springframework.stereotype.Component;
/**
 * Description:消息通知
 * Create Time: 2019-07-04 09:29
 */
@Component
public class UserNotificationModel extends BaseModel{

    /**
    * 标题
    */
    @GsExcelProperty(index = 0, description = "标题")
    private String un_title;

    /**
    * 文章id
    */
    @GsExcelProperty(index = 1, description = "文章id")
    private String news_id;

    /**
    * 文章类型
    */
    @GsExcelProperty(index = 2, description = "文章类型")
    private String news_type;

    /**
    * 通知类型
    */
    @GsExcelProperty(index = 3, description = "通知类型")
    private String un_type;

    /**
    * 回复id
    */
    @GsExcelProperty(index = 4, description = "回复id")
    private String reply_id;

    /**
    * 用户id
    */
    @GsExcelProperty(index = 5, description = "用户id")
    private String user_id;

    /**
    * 是否已读
    */
    @GsExcelProperty(index = 6, description = "是否已读")
    private String user_is_read;

    public void setUn_title(String un_title){        this.un_title=un_title;    }
 
    public String getUn_title(){        return un_title;    }
 
    public void setNews_id(String news_id){        this.news_id=news_id;    }
 
    public String getNews_id(){        return news_id;    }
 
    public void setNews_type(String news_type){        this.news_type=news_type;    }
 
    public String getNews_type(){        return news_type;    }
 
    public void setUn_type(String un_type){        this.un_type=un_type;    }
 
    public String getUn_type(){        return un_type;    }
 
    public void setReply_id(String reply_id){        this.reply_id=reply_id;    }
 
    public String getReply_id(){        return reply_id;    }
 
    public void setUser_id(String user_id){        this.user_id=user_id;    }
 
    public String getUser_id(){        return user_id;    }
 
    public void setUser_is_read(String user_is_read){        this.user_is_read=user_is_read;    }
 
    public String getUser_is_read(){        return user_is_read;    }
 
}
