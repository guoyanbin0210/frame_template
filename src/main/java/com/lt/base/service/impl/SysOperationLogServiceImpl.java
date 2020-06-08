package com.lt.base.service.impl;
import com.lt.base.dao.SysOperationLogDao;
import com.lt.base.model.SysOperationLogModel;
import com.lt.base.service.SysOperationLogService;
import com.lt.base.dao.BaseDao;
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
