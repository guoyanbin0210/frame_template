package com.lt.body.thirdPart.service;

import com.lt.base.service.BaseService;
import com.lt.body.thirdPart.model.OttServicesModel;

import java.util.List;


public interface OttServicesService extends BaseService<OttServicesModel>{
    List<OttServicesModel> selectListByType(String ser_type);
}
