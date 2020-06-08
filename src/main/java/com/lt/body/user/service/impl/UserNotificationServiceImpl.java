package com.lt.body.user.service.impl;
import com.lt.body.user.dao.UserNotificationDao;
import com.lt.body.user.model.UserNotificationModel;
import com.lt.body.user.service.UserNotificationService;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
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
