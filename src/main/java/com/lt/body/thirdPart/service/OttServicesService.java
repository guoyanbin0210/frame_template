package com.lt.body.thirdPart.service;

import com.lt.base.service.BaseService;
import com.lt.body.thirdPart.model.OttServicesModel;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:服务管理 service
 * Date: 2019-05-17
 * Time: 09:38
 */
public interface OttServicesService extends BaseService<OttServicesModel>{
    List<OttServicesModel> selectListByType(String ser_type);
}
