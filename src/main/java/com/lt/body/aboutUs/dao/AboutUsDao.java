package com.lt.body.aboutUs.dao;

import com.lt.body.aboutUs.model.AboutUsModel;
import com.lt.base.dao.BaseDao;
import com.lt.body.apiMoudel.ApiAboutMoudel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:关于我们 mapper
 * Date: 2020-02-28
 * Time: 11:18
 */
@Mapper
public interface AboutUsDao extends BaseDao<AboutUsModel>{
    //返回后台数据
    List<AboutUsModel> selectDataByHtml(@Param("menu_id") String menu_id,@Param("keyWord")String keyWord);

    //检查是否存在数据库
    Integer  selectCountByMenuId(@Param("menu_id") String menu_id);

    //返回前端数据  根据menu_id  查询详情
    ApiAboutMoudel selectDataByMenuId(@Param("menu_id") String menu_id);
}