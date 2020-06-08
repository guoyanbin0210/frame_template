package com.lt.base.service;

import com.lt.base.model.SysRolePermissionModel;

import java.util.List;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 10:17
 */
public interface SysRolePermissionService extends BaseService<SysRolePermissionModel> {
    List<SysRolePermissionModel> selectListByRoleId(String role_id);


    Integer deleteByRoleId(String role_id);

    Integer deleteByPermissionId(String permission_id);

    Integer deleteByPermissionIdAndRoleId(String permission_id, String role_id);
    SysRolePermissionModel selectOneByLoginIdAndRoleId(String permission_id, String role_id);
}
