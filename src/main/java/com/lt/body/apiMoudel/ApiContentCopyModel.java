package com.lt.body.apiMoudel;

import lombok.Data;

@Data
public class ApiContentCopyModel {

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
}
