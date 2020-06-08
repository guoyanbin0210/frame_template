package com.lt.body.comMenu.service;
import com.lt.body.apiMoudel.ApiMenuModel;
import com.lt.body.comMenu.model.MenuModel;
import com.lt.base.service.BaseService;

import java.util.List;


public interface MenuService extends BaseService<MenuModel>{

    //返回前端数据
    List<ApiMenuModel> findDataByMenu();

    //返回后台分类
    List<MenuModel> findDataByType(String type);

    //返回子集分类
    List<MenuModel> findDataById(String id,String type);

    //根据导航栏Id  查询菜单列表数据
    List<ApiMenuModel> findDataByParentId(String parent_id);
}
