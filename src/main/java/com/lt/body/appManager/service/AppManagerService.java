package com.lt.body.appManager.service;
import com.lt.body.appManager.model.AppManagerModel;
import com.lt.base.service.BaseService;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:app版本更新 service
 * Date: 2020-01-08
 * Time: 10:18
 */
public interface AppManagerService extends BaseService<AppManagerModel>{

    //查询最新版本
    AppManagerModel findDataByNewest();

    //查询历史版本
    List<AppManagerModel> findDataByHistory();

}
