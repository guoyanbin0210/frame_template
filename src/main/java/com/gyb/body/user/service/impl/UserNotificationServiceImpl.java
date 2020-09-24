package com.gyb.body.user.service.impl;
import com.gyb.body.user.dao.UserNotificationDao;
import com.gyb.body.user.model.UserNotificationModel;
import com.gyb.body.user.service.UserNotificationService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserNotificationServiceImpl extends BaseServiceImpl<UserNotificationModel> implements UserNotificationService{
    @Resource 
    private UserNotificationDao dao;
    @Override
    public BaseDao<UserNotificationModel> getBaseDao() {
        return dao;
    }
}
