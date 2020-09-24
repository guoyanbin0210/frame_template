package com.gyb.base.service.impl;
import com.gyb.base.dao.SysOperationLogDao;
import com.gyb.base.model.SysOperationLogModel;
import com.gyb.base.service.SysOperationLogService;
import com.gyb.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Description:
 * Date: 2018-12-27
 * Time: 09:41
 */
@Service
public class SysOperationLogServiceImpl extends BaseServiceImpl<SysOperationLogModel> implements SysOperationLogService{
    @Resource 
    private SysOperationLogDao dao;
    @Override
    public BaseDao<SysOperationLogModel> getBaseDao() {
        return dao;
    }
}
