package com.gyb.body.aboutUs.service.impl;

import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import com.gyb.body.aboutUs.dao.AddressDao;
import com.gyb.body.aboutUs.model.AddressModel;
import com.gyb.body.aboutUs.service.AddressService;
import com.gyb.body.apiMoudel.ApiAddressMoudel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressModel> implements AddressService {
    @Resource 
    private AddressDao dao;
    @Override
    public BaseDao<AddressModel> getBaseDao() {
        return dao;
    }

    @Override
    public ApiAddressMoudel findData() {
        return dao.selectData();
    }
}
