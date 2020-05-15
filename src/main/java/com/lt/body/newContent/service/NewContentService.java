package com.lt.body.newContent.service;
import com.lt.body.apiMoudel.ApiNewsMoudel;
import com.lt.body.newContent.model.NewContentModel;
import com.lt.base.service.BaseService;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:新闻管理 service
 * Date: 2020-02-28
 * Time: 10:34
 */
public interface NewContentService extends BaseService<NewContentModel>{


    //返回后台html数据
    List<NewContentModel> findDataByHtml(String menu_id,String parent_id,String keyWord );

    //返回前端数据  根据menu_id 查询数据
    List<ApiNewsMoudel> findDataByMenuId(String menu_id);

    //查询首页数据
    List<ApiNewsMoudel> findDataByNewsType();

    //根据id查询详情
    ApiNewsMoudel findDataById(String id);
}
