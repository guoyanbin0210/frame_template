package com.gyb.base.service;

import com.gyb.base.model.SysPermissionModel;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 09:53
 */
public interface SysPermissionService extends BaseService<SysPermissionModel> {


    /**
     * 重写原因，因为添加权限自动为管理员添加对应角色关系
     *
     * @param sysPermissionModel
     * @return
     */
    Integer insert(SysPermissionModel sysPermissionModel);

    /**
     * 当前权限被删除 意味着，和当前权限相关的信息都被删除
     *
     * @param id 数据主键
     * @return
     */
    Integer delete(String id);

    /**
     * 当前权限被删除 意味着，和当前权限相关的信息都被删除
     *
     * @param permission_band_menu_id 数据主键
     * @return
     */
    Integer deleteByMenuId(String permission_band_menu_id);

    Integer updateByMenuId( SysPermissionModel permission_band_menu_id);

    SysPermissionModel selectByMenuId(String permission_band_menu_id);



}
