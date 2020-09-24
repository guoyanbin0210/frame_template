package com.gyb.body.aboutUs.service.impl;
import com.gyb.body.aboutUs.dao.AboutUsDao;
import com.gyb.body.aboutUs.model.AboutUsModel;
import com.gyb.body.aboutUs.service.AboutUsService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import com.gyb.body.apiMoudel.ApiAboutMoudel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class AboutUsServiceImpl extends BaseServiceImpl<AboutUsModel> implements AboutUsService{
    @Resource 
    private AboutUsDao dao;
    @Override
    public BaseDao<AboutUsModel> getBaseDao() {
        return dao;
    }

    @Override
    public List<AboutUsModel> findDataByHtml(String menu_id, String keyWord) {
        return dao.selectDataByHtml(menu_id,keyWord);
    }

    @Override
    public Integer findDataByMenuId(String menu_id) {
        return dao.selectCountByMenuId(menu_id);
    }

    @Override
    public ApiAboutMoudel findDataById(String menu_id) {
        return dao.selectDataByMenuId(menu_id);
    }
}
