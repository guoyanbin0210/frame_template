package com.lt.body.comContent.service;
import com.lt.body.apiMoudel.ApiContentCopyModel;
import com.lt.body.apiMoudel.ApiContentModel;
import com.lt.body.comContent.model.ContentModel;
import com.lt.base.service.BaseService;

import java.util.List;

public interface ContentService extends BaseService<ContentModel>{

    //返回后台前端数据
    List<ContentModel> findDataByHtml(String tb_menu_id,String id,String keyWord);

    //根据二级菜单id 查询数据列表
    List<ApiContentModel> findDataByMenuId(String menu_id);

    //查询文章是否存在
    Integer findCountByParentId(String parent_id);

    //查询文章详情
    ApiContentCopyModel findDataById(String menu_id);


}
