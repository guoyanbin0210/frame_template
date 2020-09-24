package com.gyb.base.service.impl;
import com.gyb.base.dao.SysSmsLogDao;
import com.gyb.base.model.SysSmsLogModel;
import com.gyb.base.service.SysSmsLogService;
import com.gyb.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Description:
 * Date: 2019-01-02
 * Time: 01:37
 */
@Service
public class SysSmsLogServiceImpl extends BaseServiceImpl<SysSmsLogModel> implements SysSmsLogService{
    @Resource 
    private SysSmsLogDao dao;
    @Override
    public BaseDao<SysSmsLogModel> getBaseDao() {
        return dao;
    }

    @Override
    public HashMap selectSendCount(String ssl_model_code) {
        return dao.selectSendCount(ssl_model_code);
    }
}
