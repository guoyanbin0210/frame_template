package com.lt.body.comBanner.service.impl;
import com.lt.body.apiMoudel.ApiBannerModel;
import com.lt.body.comBanner.dao.BannerDao;
import com.lt.body.comBanner.model.BannerModel;
import com.lt.body.comBanner.service.BannerService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:banner管理 serviceImpl
 * Date: 2020-02-28
 * Time: 09:34
 */
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
