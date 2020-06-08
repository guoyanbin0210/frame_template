package com.lt.base.dao;

import com.lt.base.model.SysLoginLogModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysLoginLogDao extends BaseDao<SysLoginLogModel>{
    Integer selectCountByCreateBy(@Param("create_by")String create_by);
    SysLoginLogModel selectLastByCreateBy(@Param("create_by")String create_by);
}