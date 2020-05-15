package com.lt.base.service.impl;
import com.lt.base.dao.SysFileDao;
import com.lt.base.model.SysFileModel;
import com.lt.base.service.SysFileService;
import com.lt.base.dao.BaseDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Created with GaoShan.
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
