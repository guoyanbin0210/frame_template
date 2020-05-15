package com.lt.body.appManager.service.impl;
import com.lt.body.appManager.dao.AppManagerDao;
import com.lt.body.appManager.model.AppManagerModel;
import com.lt.body.appManager.service.AppManagerService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:app版本更新 serviceImpl
 * Date: 2020-01-08
 * Time: 10:18
 */
@Service
public class AppManagerServiceImpl extends BaseServiceImpl<AppManagerModel> implements AppManagerService{
    @Resource 
    private AppManagerDao dao;
    @Override
    public BaseDao<AppManagerModel> getBaseDao() {
        return dao;
    }

    @Override
    public AppManagerModel findDataByNewest() {
        return dao.selectDataByNewest();
    }

    @Override
    public List<AppManagerModel> findDataByHistory() {
        return dao.selectDataByHistory();
    }
}
