package com.lt.base.dao;

import com.lt.base.model.SysPermissionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-20
 * Time: 09:53
 */
@Mapper
public interface SysPermissionDao extends BaseDao<SysPermissionModel> {
    SysPermissionModel selectByMenuId(@Param("permission_band_menu_id") String permission_band_menu_id);
    Integer deleteByMenuId(@Param("permission_band_menu_id") String permission_band_menu_id);
    Integer updateByMenuId(SysPermissionModel sysPermissionModel);
}