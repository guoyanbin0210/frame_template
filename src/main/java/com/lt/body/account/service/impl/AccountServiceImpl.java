package com.lt.body.account.service.impl;
import com.lt.body.account.dao.AccountDao;
import com.lt.body.account.model.AccountModel;
import com.lt.body.account.service.AccountService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Created with GaoShan.
 * Description:账号管理 serviceImpl
 * Date: 2020-02-20
 * Time: 02:21
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountModel> implements AccountService{
    @Resource 
    private AccountDao dao;
    @Override
    public BaseDao<AccountModel> getBaseDao() {
        return dao;
    }
}
