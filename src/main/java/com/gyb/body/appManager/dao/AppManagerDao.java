package com.gyb.body.appManager.dao;

import com.gyb.body.appManager.model.AppManagerModel;
import com.gyb.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppManagerDao extends BaseDao<AppManagerModel>{

    //查询最新版本数据
    AppManagerModel selectDataByNewest();

    //查询历史版本
    List<AppManagerModel> selectDataByHistory();
}