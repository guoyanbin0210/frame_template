package com.lt.base.service;

import com.lt.base.model.SysLoginRoleModel;

import java.util.List;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 10:16
 */
public interface SysLoginRoleService extends BaseService<SysLoginRoleModel> {

    List<SysLoginRoleModel> selectByLoginId(String login_id);

    /**
     * 当角色被删除 ，当前角色对应的全部用户关系被清空
     *
     * @param role_id
     * @return
     */
    Integer deleteRoleId(String role_id);


    Integer deleteByLoginIdAndRoleId(String login_id, String role_id);


}
