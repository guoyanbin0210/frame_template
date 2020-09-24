package com.gyb.body.appManager.service;
import com.gyb.body.appManager.model.AppManagerModel;
import com.gyb.base.service.BaseService;

import java.util.List;


public interface AppManagerService extends BaseService<AppManagerModel>{

    //查询最新版本
    AppManagerModel findDataByNewest();

    //查询历史版本
    List<AppManagerModel> findDataByHistory();

}
