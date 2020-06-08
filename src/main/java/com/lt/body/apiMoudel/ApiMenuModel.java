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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<ApiMenuModel> getModels() {
        return models;
    }

    public void setModels(List<ApiMenuModel> models) {
        this.models = models;
    }
}
