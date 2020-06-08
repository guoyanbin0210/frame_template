package com.lt.base.service;

import com.lt.base.model.SysMenuModel;

import java.util.List;

/**
 * Description:
 * Date: 2018-12-24
 * Time: 09:03
 */
public interface SysMenuService extends BaseService<SysMenuModel> {
    /**
     * 包含新增，角色，权限，之间的关系
     *
     * @param sysMenuModel
     * @return
     */
    Integer insertRBAC(SysMenuModel sysMenuModel);

    /**
     * 包含删除菜单，角色，权限，之间的关系
     *
     * @param
     * @return
     */
    Integer deleteRBAC(String id);

    Integer updateRBAC(SysMenuModel sysMenuModel);

    List<SysMenuModel> selectRBAC();
}
