package com.lt.body.appManager.service;
import com.lt.body.appManager.model.AppManagerModel;
import com.lt.base.service.BaseService;

import java.util.List;


public interface AppManagerService extends BaseService<AppManagerModel>{

    //查询最新版本
    AppManagerModel findDataByNewest();

    //查询历史版本
    List<AppManagerModel> findDataByHistory();

}
