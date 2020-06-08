package com.lt.base.service.impl;

import com.lt.base.dao.SysRoleDao;
import com.lt.base.model.*;
import com.lt.base.service.*;
import com.lt.base.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Description:
 * Date: 2018-12-20
 * Time: 09:52
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleModel> implements SysRoleService {
    @Resource
    private SysRoleDao dao;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysLoginRoleService sysLoginRoleService;

    @Override
    public BaseDao<SysRoleModel> getBaseDao() {
        return dao;
    }


    @Override
    public SysRoleModel selectDetailsById(String id) {
        SysRoleModel sysRoleModel = dao.selectById(id);
        if (sysRoleModel != null) {
            return getRoleDetails(sysRoleModel);
        }
        return null;
    }

    @Override
    public List<SysRoleModel> selectListDetails(SysRoleModel model) {
        ArrayList<SysRoleModel> sysRoleModels = sysRoleService.selectListByModel(model);
        if (sysRoleModels != null) {
            //用于收集任务
            List<FutureTask<SysRoleModel>> futureTasks = new ArrayList<>();
            //创建线程池
            ExecutorService pool = Executors.newFixedThreadPool(5);
            for (SysRoleModel sysRoleModel : sysRoleModels) {
                FutureTask<SysRoleModel> futureTask =
                        new FutureTask<>(() -> getRoleDetails(sysRoleModel));
                pool.submit(futureTask);
                futureTasks.add(futureTask);
            }
            ArrayList<SysRoleModel> resultData = new ArrayList<>();
            for (FutureTask<SysRoleModel> futureTask : futureTasks) {
                try {
                    resultData.add(futureTask.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //关闭线程池
            pool.shutdown();
            return resultData;
        }
        return null;
    }

    @Override
    public Integer deleteAllChild(String roleId) {
        int success = 0;
        //角色对应的用户关系
        success = sysLoginRoleService.deleteRoleId(roleId);
        //角色对应的权限关系
        success += sysRolePermissionService.deleteByRoleId(roleId);
        return success;
    }


    private SysRoleModel getRoleDetails(SysRoleModel sysRoleModel) {
        String roleModelId = sysRoleModel.getId();
        //根据角色id查询对应角色关系
        List<SysRolePermissionModel> sysRolePermissionModels = sysRolePermissionService.selectListByRoleId(roleModelId);
        if (null != sysRolePermissionModels && sysRolePermissionModels.size() > 0) {

            //用于收集任务
            List<FutureTask<SysPermissionModel>> futureTasks = new ArrayList<>();
            //创建线程池
            ExecutorService pool = Executors.newFixedThreadPool(5);

            for (SysRolePermissionModel sysRolePermissionModel : sysRolePermissionModels) {
                FutureTask<SysPermissionModel> futureTask =
                        new FutureTask<>(() -> sysPermissionService.selectById(sysRolePermissionModel.getPermission_id()));
                pool.submit(futureTask);

                futureTasks.add(futureTask);
            }
            ArrayList<SysPermissionModel> sysPermissionModels = new ArrayList<>();
            for (FutureTask<SysPermissionModel> futureTask : futureTasks) {
                try {
                    SysPermissionModel sysPermissionModel = futureTask.get();
                    sysPermissionModels.add(sysPermissionModel);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if(sysPermissionModels.size()>0){
                sysRoleModel.setPermissions(sysPermissionModels);
            }
            //关闭线程池
            pool.shutdown();
        }
        return sysRoleModel;
    }

}
