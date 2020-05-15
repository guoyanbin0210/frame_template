package com.lt.base.service.impl;

import com.lt.base.dao.BaseDao;
import com.lt.base.dao.BaseDirDao;
import com.lt.base.model.BaseDirModel;
import com.lt.base.service.BaseDirService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:字典 serviceImpl
 * Date: 2019-05-07
 * Time: 02:40
 */
@Service
public class BaseDirServiceImpl extends BaseServiceImpl<BaseDirModel> implements BaseDirService {

    @Resource
    private BaseDirDao dao;

    @Override
    public BaseDao<BaseDirModel> getBaseDao() {
        return dao;
    }

    @Override
    public BaseDirModel selectOneByName(String bd_name) {
        return dao.selectOneByName(bd_name);
    }

    @Override
    public List<BaseDirModel> selectListByType(String bd_type) {
        return dao.selectListByType(bd_type);
    }



    @Override
    public List<BaseDirModel> selectListByTypeAndAvailable(String bd_type, Integer base_available) {
        return dao.selectListByTypeAndAvailable(bd_type,base_available);
    }

    @Override
    public List<BaseDirModel> selectListLikeName(String bd_name) {
        return dao.selectListLikeName(bd_name);
    }

    @Override
    public List<BaseDirModel> selectListByTypeAndAvailableAndBdData(String bd_type, Integer base_available, String bd_data) {
        return dao.selectListByTypeAndAvailableAndBdData(bd_type,base_available,bd_data);
    }

    @Override
    public List<BaseDirModel> selectListByPTypeAndPId(String bd_type,String p_id, Integer base_available) {
        return dao.selectListByPTypeAndPId( bd_type,p_id,base_available);
    }


}
