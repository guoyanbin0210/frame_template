package com.gyb.body.business.service.impl;
import com.gyb.body.business.dao.UserDao;
import com.gyb.body.business.model.UserModel;
import com.gyb.body.business.service.UserService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.scheduling.annotation.Async;
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

    @Override
    @Async
    public void test(){
        System.out.println("userService Test");
        System.out.println("线程侧睡"+Thread.currentThread().getName());
    }
}
