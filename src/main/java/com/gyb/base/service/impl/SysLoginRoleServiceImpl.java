package com.gyb.base.service.impl;

import com.gyb.base.dao.SysLoginRoleDao;
import com.gyb.base.model.SysLoginRoleModel;
import com.gyb.base.service.SysLoginRoleService;
import com.gyb.base.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 10:16
 */
@Service
public class SysLoginRoleServiceImpl extends BaseServiceImpl<SysLoginRoleModel> implements SysLoginRoleService {
    @Resource
    private SysLoginRoleDao dao;

    @Override
    public BaseDao<SysLoginRoleModel> getBaseDao() {
        return dao;
    }


    @Override
    public List<SysLoginRoleModel> selectByLoginId(String login_id) {
        return dao.selectByLoginId(login_id);
    }

    @Override
    public Integer deleteRoleId(String role_id) {
        return dao.deleteRoleId(role_id);
    }

    @Override
    public Integer deleteByLoginIdAndRoleId(String login_id, String role_id) {
        return dao.deleteByLoginIdAndRoleId(login_id, role_id);
    }
}
