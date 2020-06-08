package com.lt.body.comContent.service.impl;
import com.lt.body.apiMoudel.ApiContentCopyModel;
import com.lt.body.apiMoudel.ApiContentModel;
import com.lt.body.comContent.dao.ContentDao;
import com.lt.body.comContent.model.ContentModel;
import com.lt.body.comContent.service.ContentService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ContentServiceImpl extends BaseServiceImpl<ContentModel> implements ContentService{
    @Resource 
    private ContentDao dao;
    @Override
    public BaseDao<ContentModel> getBaseDao() {
        return dao;
    }

    //返回前端数据
    @Override
    public List<ContentModel> findDataByHtml(String tb_menu_id, String id, String keyWord) {
        return dao.selectDataByHtml(tb_menu_id,id,keyWord);
    }

    @Override
    public List<ApiContentModel> findDataByMenuId(String menu_id) {
        return dao.selectDataByMenuId(menu_id);
    }

    @Override
    public Integer findCountByParentId(String parent_id) {
        return dao.selectMenuId(parent_id);
    }

    @Override
    public ApiContentCopyModel findDataById(String menu_id) {
        return dao.selectDataById(menu_id);
    }
}
