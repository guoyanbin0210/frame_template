package com.lt.body.business.service.impl;
import com.lt.body.business.dao.UserDao;
import com.lt.body.business.model.UserModel;
import com.lt.body.business.service.UserService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * * Created with GuoYanBin.
 * Description:信息 serviceImpl
 * Date: 2020-08-07
 * Time: 10:55
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel> implements UserService{
    @Resource 
    private UserDao dao;
    @Override
    public BaseDao<UserModel> getBaseDao() {
        return dao;
    }
}
