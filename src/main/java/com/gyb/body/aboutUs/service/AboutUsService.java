package com.gyb.body.aboutUs.service;
import com.gyb.body.aboutUs.model.AboutUsModel;
import com.gyb.base.service.BaseService;
import com.gyb.body.apiMoudel.ApiAboutMoudel;

import java.util.List;

public interface AboutUsService extends BaseService<AboutUsModel>{

    //返回后台数据
    List<AboutUsModel> findDataByHtml(String menu_id,String keyWord);

    //检查是否存在数据库
    Integer findDataByMenuId(String menu_id);

    //根据menu_id 查询详情
    ApiAboutMoudel findDataById(String menu_id);
}
