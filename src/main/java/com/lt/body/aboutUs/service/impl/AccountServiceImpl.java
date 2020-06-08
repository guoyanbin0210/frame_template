package com.lt.body.aboutUs.service.impl;

import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import com.lt.body.aboutUs.dao.AccountDao;
import com.lt.body.aboutUs.model.AccountModel;
import com.lt.body.aboutUs.service.AccountService;
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
