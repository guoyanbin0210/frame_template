package com.lt.base.service.impl;

import com.lt.base.constant.BaseConstant;
import com.lt.base.dao.BaseDao;
import com.lt.base.dao.SysPermissionDao;
import com.lt.base.model.SysPermissionModel;
import com.lt.base.model.SysRolePermissionModel;
import com.lt.base.service.SysPermissionService;
import com.lt.base.service.SysRolePermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 09:53
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionModel> implements SysPermissionService {

    private Logger logger = LogManager.getLogger(getClass());

    @Resource
    private SysPermissionDao dao;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public BaseDao<SysPermissionModel> getBaseDao() {
        return dao;
    }

    @Override
    public Integer insert(SysPermissionModel sysPermissionModel) {
        Integer insert = dao.insert(sysPermissionModel);
        if (insert != 0) {
            //绑定权限到管理员角色中
            SysRolePermissionModel sysRolePermissionModel = new SysRolePermissionModel();
            sysRolePermissionModel.init(sysRolePermissionModel);
            sysRolePermissionModel.setRole_id(BaseConstant.ROLE_ADMIN_ID);//
            sysRolePermissionModel.setPermission_id(sysPermissionModel.getId());
            sysRolePermissionService.insert(sysRolePermissionModel);
        }
        return insert;
    }

    @Override
    public Integer deleteByMenuId(String permission_band_menu_id) {
        SysPermissionModel sysPermissionModel = selectByMenuId(permission_band_menu_id);
        Integer result = dao.deleteByMenuId(permission_band_menu_id);
        if (result != 0) {
            if (sysPermissionModel != null) {
                Integer integer = sysRolePermissionService.deleteByPermissionId(sysPermissionModel.getId());
                if (integer < 1) {
                    logger.error("权限角色关系删除异常：" + permission_band_menu_id);
                }
            } else {
                logger.error("权限删除异常：" + permission_band_menu_id);
            }
        }
        return result;
    }

    @Override
    public Integer delete(String id) {
        Integer result = dao.delete(id);
        if (result != 0) {
            sysRolePermissionService.deleteByPermissionId(id);
        }
        return result;
    }


    @Override
    public Integer updateByMenuId(SysPermissionModel sysPermissionModel) {
        return dao.updateByMenuId(sysPermissionModel);
    }


    @Override
    public SysPermissionModel selectByMenuId(String permission_band_menu_id) {
        return dao.selectByMenuId(permission_band_menu_id);
    }

}
