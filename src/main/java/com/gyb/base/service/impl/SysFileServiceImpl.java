package com.gyb.base.service.impl;
import com.gyb.base.dao.SysFileDao;
import com.gyb.base.model.SysFileModel;
import com.gyb.base.service.SysFileService;
import com.gyb.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Description:
 * Date: 2019-01-24
 * Time: 01:18
 */
@Service
public class SysFileServiceImpl extends BaseServiceImpl<SysFileModel> implements SysFileService{
    @Resource 
    private SysFileDao dao;
    @Override
    public BaseDao<SysFileModel> getBaseDao() {
        return dao;
    }
}
