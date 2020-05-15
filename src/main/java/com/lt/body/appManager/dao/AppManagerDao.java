package com.lt.body.appManager.dao;

import com.lt.body.appManager.model.AppManagerModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:app版本更新 mapper
 * Date: 2020-01-08
 * Time: 10:18
 */
@Mapper
public interface AppManagerDao extends BaseDao<AppManagerModel>{

    //查询最新版本数据
    AppManagerModel selectDataByNewest();

    //查询历史版本
    List<AppManagerModel> selectDataByHistory();
}