package com.lt.body.user.dao;

import com.lt.body.user.model.UserMainModel;
import com.lt.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;


@Mapper
public interface UserMainDao extends BaseDao<UserMainModel>{
    UserMainModel selectByPhone(@Param("user_phone") String phone);

    Integer selectExitByPhone(@Param("user_phone")String phone);

    HashMap<String,Object> selectNameAndHeadImg(@Param("id")String id);

    UserMainModel selectOneByWxOpenId(@Param("wx_openid") String wx_openid);

    UserMainModel selectOneByQQOpenId(@Param("qq_openid")String qq_openid);

    UserMainModel selectOneByWbOpenId(@Param("wb_openid")String wb_openid);

    UserMainModel selectOneByWxUnionId(@Param("wx_unionId")String wx_unionId);


}