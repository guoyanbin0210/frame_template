package com.lt.body.comBanner.service;
import com.lt.body.apiMoudel.ApiBannerModel;
import com.lt.body.comBanner.model.BannerModel;
import com.lt.base.service.BaseService;

import java.util.List;


public interface BannerService extends BaseService<BannerModel>{
    
    List<ApiBannerModel> findDataByType(String type);

    //查询后台数据
    List<BannerModel> findDataByHtml();
}
