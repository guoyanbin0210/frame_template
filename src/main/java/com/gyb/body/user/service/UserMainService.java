package com.gyb.body.user.service;

import com.gyb.base.service.BaseService;
import com.gyb.body.user.model.UserMainModel;

import java.util.HashMap;


public interface UserMainService extends BaseService<UserMainModel>{
    //验证是否存在
    Integer selectExitByPhone(String phone);
    //根据手机号码查询用户信息
    UserMainModel selectByPhone(String phone);

    HashMap<String,Object> selectNameAndHeadImg (String id);

    UserMainModel selectOneByWxOpenId(String wx_openid);
    UserMainModel selectOneByQQOpenId(String qq_openid);
    UserMainModel selectOneByWbOpenId(String wb_openid);

    UserMainModel selectOneByWxUnionId(String wx_unionId);


}
