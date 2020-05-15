package com.lt.base.service.impl;
import com.lt.base.dao.SysLoginLogDao;
import com.lt.base.model.SysLoginLogModel;
import com.lt.base.service.SysLoginLogService;
import com.lt.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-21
 * Time: 02:01
 */
@Service
public class SysLoginLogServiceImpl extends BaseServiceImpl<SysLoginLogModel> implements SysLoginLogService{
    @Resource 
    private SysLoginLogDao dao;
    @Override
    public BaseDao<SysLoginLogModel> getBaseDao() {
        return dao;
    }

    @Override
    public Integer selectCountByCreateBy(String create_by) {
        return dao.selectCountByCreateBy(create_by);
    }

    @Override
    public SysLoginLogModel selectLastByCreateBy(String create_by) {
        return dao.selectLastByCreateBy(create_by);

    }
}
