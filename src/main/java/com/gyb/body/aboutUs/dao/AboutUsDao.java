package com.gyb.body.aboutUs.dao;

import com.gyb.body.aboutUs.model.AboutUsModel;
import com.gyb.base.dao.BaseDao;
import com.gyb.body.apiMoudel.ApiAboutMoudel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AboutUsDao extends BaseDao<AboutUsModel>{
    //返回后台数据
    List<AboutUsModel> selectDataByHtml(@Param("menu_id") String menu_id,@Param("keyWord")String keyWord);

    //检查是否存在数据库
    Integer  selectCountByMenuId(@Param("menu_id") String menu_id);

    //返回前端数据  根据menu_id  查询详情
    ApiAboutMoudel selectDataByMenuId(@Param("menu_id") String menu_id);
}