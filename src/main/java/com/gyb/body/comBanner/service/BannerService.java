package com.gyb.body.comBanner.service;
import com.gyb.body.apiMoudel.ApiBannerModel;
import com.gyb.body.comBanner.model.BannerModel;
import com.gyb.base.service.BaseService;

import java.util.List;


public interface BannerService extends BaseService<BannerModel>{
    
    List<ApiBannerModel> findDataByType(String type);

    //查询后台数据
    List<BannerModel> findDataByHtml();
}
