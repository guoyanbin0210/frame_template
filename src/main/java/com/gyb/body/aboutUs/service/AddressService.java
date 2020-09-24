package com.gyb.body.aboutUs.service;

import com.gyb.base.service.BaseService;
import com.gyb.body.aboutUs.model.AddressModel;
import com.gyb.body.apiMoudel.ApiAddressMoudel;


public interface AddressService extends BaseService<AddressModel>{

    ApiAddressMoudel findData();

}