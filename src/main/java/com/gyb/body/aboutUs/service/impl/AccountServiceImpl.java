package com.gyb.body.aboutUs.service.impl;

import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import com.gyb.body.aboutUs.dao.AccountDao;
import com.gyb.body.aboutUs.model.AccountModel;
import com.gyb.body.aboutUs.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountModel> implements AccountService {
    @Resource 
    private AccountDao dao;
    @Override
    public BaseDao<AccountModel> getBaseDao() {
        return dao;
    }
}
