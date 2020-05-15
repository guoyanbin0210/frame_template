package com.lt.base.service;

import com.lt.base.model.SysRoleModel;

import java.util.List;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-20
 * Time: 09:52
 */
public interface SysRoleService extends BaseService<SysRoleModel> {

    /**
     * 查询一条角色包含的权限
     *
     * @param id
     * @return
     */
    SysRoleModel selectDetailsById(String id);

    /**
     * 查询多条角色包含的权限
     *
     * @param model
     * @return
     */
    List<SysRoleModel> selectListDetails(SysRoleModel model);


    /**
     * 删除角色
     * 角色对应的用户关系
     * 角色对应的权限关系
     * <p>
     * 但是不删除权限
     *
     * @param role_id
     * @return
     */
    Integer deleteAllChild(String role_id);


}


