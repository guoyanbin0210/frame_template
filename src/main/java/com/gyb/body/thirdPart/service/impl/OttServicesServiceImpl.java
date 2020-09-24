package com.gyb.body.thirdPart.service.impl;
import com.gyb.body.thirdPart.dao.OttServicesDao;
import com.gyb.body.thirdPart.model.OttServicesModel;
import com.gyb.body.thirdPart.service.OttServicesService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


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
