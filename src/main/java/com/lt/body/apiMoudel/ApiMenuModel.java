package com.lt.body.apiMoudel;

import lombok.Data;

import java.util.List;

@Data
public class ApiMenuModel {

    private String id;

    private String name;

    private Integer sort;

    private String menu_url;

    private Integer level;

    private List<ApiMenuModel> models;

}
