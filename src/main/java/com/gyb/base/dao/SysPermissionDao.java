package com.gyb.base.dao;

import com.gyb.base.model.SysPermissionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysPermissionDao extends BaseDao<SysPermissionModel> {
    SysPermissionModel selectByMenuId(@Param("permission_band_menu_id") String permission_band_menu_id);
    Integer deleteByMenuId(@Param("permission_band_menu_id") String permission_band_menu_id);
    Integer updateByMenuId(SysPermissionModel sysPermissionModel);
}