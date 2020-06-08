package com.lt.base.service.impl;

import com.lt.base.constant.BaseConstant;
import com.lt.base.dao.SysMenuDao;
import com.lt.base.model.*;
import com.lt.base.service.*;
import com.lt.base.dao.BaseDao;
import com.lt.base.util.BaseUtils;
import com.lt.base.util.PinYinHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Date: 2018-12-24
 * Time: 09:03
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuModel> implements SysMenuService {
    private Logger logger = LogManager.getLogger(getClass());

    @Resource
    private SysMenuDao dao;

    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public BaseDao<SysMenuModel> getBaseDao() {
        return dao;
    }

    @Override
    public Integer insertRBAC(SysMenuModel sysMenuModel) {
        //添加菜单
        Integer result = insert(sysMenuModel);
        if (result != 0) {
            //添加菜单对应的权限
            SysPermissionModel sysPermissionModel = new SysPermissionModel();
            sysPermissionModel.init(sysPermissionModel);
            sysPermissionModel.setPermission_name(sysMenuModel.getMenu_name());
            sysPermissionModel.setPermission_code(BaseConstant.PERMISSION_MENU_PRE + PinYinHelper.getFirstSpell(sysMenuModel.getMenu_name()).toUpperCase());
            sysPermissionModel.setPermission_band_menu_id(sysMenuModel.getId());
            sysPermissionModel.setPermission_type(BaseConstant.PERMISSION_TYPE_ENUM.TYPE_MENU.getCode());
            sysPermissionService.insert(sysPermissionModel);
        }
        return result;
    }

    @Override
    public Integer deleteRBAC(String id) {
        //删除菜单
        Integer result = delete(id);
        if (result != 0) {
            //删除对应权限
            sysPermissionService.deleteByMenuId(id);//此处自动删除权限对应的角色关系
        }
        return result;
    }

    @Override
    public Integer updateRBAC(SysMenuModel sysMenuModel) {
        Integer result = update(sysMenuModel);
        if (result != 0) {
            SysPermissionModel sysPermissionModel = new SysPermissionModel();
            sysPermissionModel.setModify_by(sysMenuModel.getModify_by());
            sysPermissionModel.setModify_time(sysMenuModel.getModify_time());
            sysPermissionModel.setPermission_name(sysMenuModel.getMenu_name());
            sysPermissionModel.setPermission_code(BaseConstant.PERMISSION_MENU_PRE + PinYinHelper.getFirstSpell(sysMenuModel.getMenu_name()).toUpperCase());
            sysPermissionModel.setPermission_band_menu_id(sysMenuModel.getId());
            sysPermissionService.updateByMenuId(sysPermissionModel);
        }
        return result;
    }

    @Override
    public List<SysMenuModel> selectRBAC() {
        ArrayList<SysMenuModel> resultList = new ArrayList<>();
        SysLoginModel currLoginModel = BaseUtils.getCurrLoginModel();

        if (currLoginModel != null) {
            List<SysRoleModel> roles = currLoginModel.getRoles();
            if (roles != null) {
                for (SysRoleModel role : roles) {
                    List<SysPermissionModel> permissions = role.getPermissions();
                    if (permissions != null) {
                        for (SysPermissionModel permission : permissions) {
                            String menuId = permission.getPermission_band_menu_id();
                            SysMenuModel sysMenuModel = selectById(menuId);
                            if (sysMenuModel != null) {
                                resultList.add(sysMenuModel);
                            }
                        }
                    }
                }
            }
            if (currLoginModel.getUser_name().equals("admin")) {
                SysMenuModel sysMenuModel = new SysMenuModel();
                sysMenuModel.setMenu_name("菜单管理");
                sysMenuModel.setMenu_url("MenuTree.html");
                sysMenuModel.setMenu_level(-1);
            }
        } else {
            logger.error("用户登录信息异常");
        }
        return resultList;
    }
}
