package com.lt.base.service.impl;
import com.lt.base.dao.SysSmsModelDao;
import com.lt.base.model.SysSmsModelModel;
import com.lt.base.service.SysSmsModelService;
import com.lt.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Description:
 * Date: 2019-02-14
 * Time: 09:32
 */
@Service
public class SysSmsModelServiceImpl extends BaseServiceImpl<SysSmsModelModel> implements SysSmsModelService{
    @Resource 
    private SysSmsModelDao dao;
    @Override
    public BaseDao<SysSmsModelModel> getBaseDao() {
        return dao;
    }

    @Override
    public SysSmsModelModel selectModelByCode(String ssm_code) {
        return dao.selectModelByCode(ssm_code);
    }
}
