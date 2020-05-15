package com.lt.body.thirdPart.service.impl;
import com.lt.body.thirdPart.dao.OttServicesDao;
import com.lt.body.thirdPart.model.OttServicesModel;
import com.lt.body.thirdPart.service.OttServicesService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:服务管理 serviceImpl
 * Date: 2019-05-17
 * Time: 09:38
 */
@Service
public class OttServicesServiceImpl extends BaseServiceImpl<OttServicesModel> implements OttServicesService{
    @Resource 
    private OttServicesDao dao;
    @Override
    public BaseDao<OttServicesModel> getBaseDao() {
        return dao;
    }

    @Override
    public List<OttServicesModel> selectListByType(String ser_type) {
        return dao.selectListByType(ser_type);
    }
}
