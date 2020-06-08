package com.lt.body.user.service.impl;

import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import com.lt.body.user.dao.UserMainDao;
import com.lt.body.user.model.UserMainModel;
import com.lt.body.user.service.UserMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class UserMainServiceImpl extends BaseServiceImpl<UserMainModel> implements UserMainService{
    @Resource 
    private UserMainDao dao;
    @Override
    public BaseDao<UserMainModel> getBaseDao() {
        return dao;
    }

    @Override
    public Integer selectExitByPhone(String phone) {
        return dao.selectExitByPhone(phone);
    }

    @Override
    public UserMainModel selectByPhone(String phone) {
        return dao.selectByPhone(phone);
    }

    @Override
    public HashMap<String,Object> selectNameAndHeadImg(String id) {
        return dao.selectNameAndHeadImg(id);
    }

    @Override
    public UserMainModel selectOneByWxOpenId(String wx_openid) {
        return dao.selectOneByWxOpenId(wx_openid);
    }

    @Override
    public UserMainModel selectOneByQQOpenId(String qq_openid) {
        return dao.selectOneByQQOpenId(qq_openid);

    }

    @Override
    public UserMainModel selectOneByWbOpenId(String wb_openid) {
        return dao.selectOneByWbOpenId(wb_openid);

    }

    @Override
    public UserMainModel selectOneByWxUnionId(String wx_unionId) {
        return dao.selectOneByWxUnionId(wx_unionId);
    }
}
