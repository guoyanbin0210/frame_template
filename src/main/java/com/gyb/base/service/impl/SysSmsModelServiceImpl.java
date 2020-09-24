package com.gyb.base.service.impl;
import com.gyb.base.dao.SysSmsModelDao;
import com.gyb.base.model.SysSmsModelModel;
import com.gyb.base.service.SysSmsModelService;
import com.gyb.base.dao.BaseDao;
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
