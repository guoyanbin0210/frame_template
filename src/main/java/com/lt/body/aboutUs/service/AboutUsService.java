package com.lt.body.aboutUs.service;
import com.lt.body.aboutUs.model.AboutUsModel;
import com.lt.base.service.BaseService;
import com.lt.body.apiMoudel.ApiAboutMoudel;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:关于我们 service
 * Date: 2020-02-28
 * Time: 11:18
 */
public interface AboutUsService extends BaseService<AboutUsModel>{

    //返回后台数据
    List<AboutUsModel> findDataByHtml(String menu_id,String keyWord);

    //检查是否存在数据库
    Integer findDataByMenuId(String menu_id);

    //根据menu_id 查询详情
    ApiAboutMoudel findDataById(String menu_id);
}
