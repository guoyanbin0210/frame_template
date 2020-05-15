package com.lt.body.apiMoudel;

import lombok.Data;

import java.util.Date;

@Data
public class ApiNewsMoudel {

    private String id;

    private String news_title;

    private String title_url;

    private Date publish_date;

    private String author;

    private String news_contnt;

    private String news_about;

}
