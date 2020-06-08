package com.lt.body.aboutUs.service;

import com.lt.base.service.BaseService;
import com.lt.body.aboutUs.model.AddressModel;
import com.lt.body.apiMoudel.ApiAddressMoudel;


public interface AddressService extends BaseService<AddressModel>{

    ApiAddressMoudel findData();

}