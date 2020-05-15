package com.lt.body.user.dao;

import com.lt.body.user.model.UserNotificationModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
/**
 * Created with GaoShan.
 * Description:消息通知 mapper
 * Date: 2019-07-04
 * Time: 09:29
 */
@Mapper
public interface UserNotificationDao extends BaseDao<UserNotificationModel>{
}