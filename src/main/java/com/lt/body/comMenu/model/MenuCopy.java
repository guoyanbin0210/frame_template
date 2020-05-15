package com.lt.body.comMenu.model;

import lombok.Data;

@Data
public class MenuCopy {

    private String id;

    private String menu_name;

    private String menu_parent_id;

    private Integer menu_level;
    /**
     * 排序
     */
    private Integer menu_sort;
}
