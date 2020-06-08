package com.lt.body.newContent.service.impl;
import com.lt.body.apiMoudel.ApiNewsMoudel;
import com.lt.body.newContent.dao.NewContentDao;
import com.lt.body.newContent.model.NewContentModel;
import com.lt.body.newContent.service.NewContentService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class NewContentServiceImpl extends BaseServiceImpl<NewContentModel> implements NewContentService{
    @Resource 
    private NewContentDao dao;
    @Override
    public BaseDao<NewContentModel> getBaseDao() {
        return dao;
    }

    @Override
    public List<NewContentModel> findDataByHtml(String menu_id, String parent_id, String keyWord) {
        return dao.selectDataByHtml(menu_id,parent_id,keyWord);
    }

    @Override
    public List<ApiNewsMoudel> findDataByMenuId(String menu_id) {
        return dao.selectDataByMenuId(menu_id);
    }

    @Override
    public List<ApiNewsMoudel> findDataByNewsType() {
        return dao.selectDataByType();
    }

    @Override
    public ApiNewsMoudel findDataById(String id) {
        return dao.selectDataById(id);
    }
}
