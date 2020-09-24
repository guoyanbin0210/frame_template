package com.gyb.body.comBanner.service.impl;
import com.gyb.body.apiMoudel.ApiBannerModel;
import com.gyb.body.comBanner.dao.BannerDao;
import com.gyb.body.comBanner.model.BannerModel;
import com.gyb.body.comBanner.service.BannerService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class BannerServiceImpl extends BaseServiceImpl<BannerModel> implements BannerService{
    @Resource 
    private BannerDao dao;
    @Override
    public BaseDao<BannerModel> getBaseDao() {
        return dao;
    }
    @Override
    public List<ApiBannerModel> findDataByType(String type) {
        Integer sum = Integer.parseInt(type);
        if (sum >= 2){
            return dao.selectDataByType(type);
        }
        return dao.findDataByType(type);
    }

    @Override
    public List<BannerModel> findDataByHtml() {
        return dao.selectDataByHtml();
    }
}
