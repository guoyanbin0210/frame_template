package com.lt.base.dao;

import com.lt.base.model.SysLoginLogModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-21
 * Time: 02:01
 */
@Mapper
public interface SysLoginLogDao extends BaseDao<SysLoginLogModel>{
    Integer selectCountByCreateBy(@Param("create_by")String create_by);
    SysLoginLogModel selectLastByCreateBy(@Param("create_by")String create_by);
}