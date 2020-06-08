package com.lt.body.newContent.dao;

import com.lt.body.apiMoudel.ApiNewsMoudel;
import com.lt.body.newContent.model.NewContentModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NewContentDao extends BaseDao<NewContentModel>{

    //返回后台页面数据
    List<NewContentModel> selectDataByHtml(@Param("menu_id") String menu_id, @Param("parent_id")String parent_id, @Param("keyWord")String keyWord);

    //返回前端数据
    List<ApiNewsMoudel> selectDataByMenuId(@Param("menu_id") String menu_id);

    //查询首页数据
    List<ApiNewsMoudel> selectDataByType();

    //根据id查询详情
    ApiNewsMoudel selectDataById(@Param("id") String id);

}