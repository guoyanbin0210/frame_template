package com.lt.base.dao;

import com.lt.base.model.SysRolePermissionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SysRolePermissionDao extends BaseDao<SysRolePermissionModel>{
    List<SysRolePermissionModel> selectListByRoleId(@Param("role_id")String role_id);

    Integer deleteByRoleId(@Param("role_id")String role_id);

    Integer deleteByPermissionId(@Param("permission_id")String permission_id);

    Integer deleteByPermissionIdAndRoleId(@Param("permission_id")String permission_id, @Param("role_id") String role_id);

    SysRolePermissionModel selectOneByLoginIdAndRoleId(@Param("permission_id")String permission_id,@Param("role_id") String role_id);
}