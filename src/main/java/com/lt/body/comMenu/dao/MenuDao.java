package com.lt.body.comMenu.dao;

import com.lt.body.comMenu.model.MenuCopy;
import com.lt.body.comMenu.model.MenuModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao extends BaseDao<MenuModel>{

    //根据类型查询数据
    List<MenuModel> selectMenu(@Param("type") String type);

    //根据id 查询子集菜单
    List<MenuModel> selectMenuById(@Param("id")String id,@Param("type") String type);

    //返回前端数据
    List<MenuModel> selectMenuByParentId(@Param("parent_id") String parent_id);








}