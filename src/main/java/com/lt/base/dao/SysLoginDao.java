package com.lt.base.dao;

import com.lt.base.model.SysLoginModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-17
 * Time: 11:36
 */
@Mapper
public interface SysLoginDao extends BaseDao<SysLoginModel>{
    SysLoginModel selectModelByUserName(@Param("user_name")String user_name);

}