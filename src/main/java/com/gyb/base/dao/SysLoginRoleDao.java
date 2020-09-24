package com.gyb.base.dao;

import com.gyb.base.model.SysLoginRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SysLoginRoleDao extends BaseDao<SysLoginRoleModel>{

    List<SysLoginRoleModel> selectByLoginId(@Param("login_id")String login_id);

    Integer deleteRoleId(@Param("role_id")String role_id);

    Integer deleteByLoginIdAndRoleId(@Param("login_id")String login_id,@Param("role_id")String role_id);

}