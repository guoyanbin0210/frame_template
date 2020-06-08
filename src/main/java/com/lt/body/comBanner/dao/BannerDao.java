package com.lt.body.comBanner.dao;

import com.lt.body.apiMoudel.ApiBannerModel;
import com.lt.body.comBanner.model.BannerModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BannerDao extends BaseDao<BannerModel>{

    //返回前端数据
    List<ApiBannerModel> findDataByType(@Param("type") String type);

    //返回前端数据
    List<ApiBannerModel> selectDataByType(@Param("type") String type);

    //返回后台html数据
    List<BannerModel> selectDataByHtml();


}