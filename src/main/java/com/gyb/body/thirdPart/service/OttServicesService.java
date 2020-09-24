package com.gyb.body.thirdPart.service;

import com.gyb.base.service.BaseService;
import com.gyb.body.thirdPart.model.OttServicesModel;

import java.util.List;


public interface OttServicesService extends BaseService<OttServicesModel>{
    List<OttServicesModel> selectListByType(String ser_type);
}
