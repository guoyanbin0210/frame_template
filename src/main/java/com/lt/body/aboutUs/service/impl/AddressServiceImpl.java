package com.lt.body.aboutUs.service.impl;

import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import com.lt.body.aboutUs.dao.AddressDao;
import com.lt.body.aboutUs.model.AddressModel;
import com.lt.body.aboutUs.service.AddressService;
import com.lt.body.apiMoudel.ApiAddressMoudel;
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
