package com.gyb.base.service.impl;

import com.gyb.base.dao.BaseDao;
import com.gyb.base.dao.SysRolePermissionDao;
import com.gyb.base.model.SysRolePermissionModel;
import com.gyb.base.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 10:17
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionModel> implements SysRolePermissionService {
    @Resource
    private SysRolePermissionDao dao;

    @Override
    public BaseDao<SysRolePermissionModel> getBaseDao() {
        return dao;
    }

    @Override
    public List<SysRolePermissionModel> selectListByRoleId(String role_id) {
        return dao.selectListByRoleId(role_id);
    }

    @Override
    public Integer deleteByRoleId(String role_id) {
        return dao.deleteByRoleId(role_id);
    }

    @Override
    public Integer deleteByPermissionId(String permission_id) {
        return dao.deleteByPermissionId(permission_id);
    }

    @Override
    public Integer deleteByPermissionIdAndRoleId(String permission_id, String role_id) {
        return dao.deleteByPermissionIdAndRoleId(permission_id, role_id);
    }

    @Override
    public SysRolePermissionModel selectOneByLoginIdAndRoleId(String permission_id, String role_id) {
        return dao.selectOneByLoginIdAndRoleId(permission_id,role_id);
    }


}
