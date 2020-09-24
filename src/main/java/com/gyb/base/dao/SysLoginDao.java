package com.gyb.base.dao;

import com.gyb.base.model.SysLoginModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysLoginDao extends BaseDao<SysLoginModel>{
    SysLoginModel selectModelByUserName(@Param("user_name")String user_name);

}