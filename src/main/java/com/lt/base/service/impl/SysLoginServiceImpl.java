package com.lt.base.service.impl;

import com.lt.base.dao.BaseDao;
import com.lt.base.dao.SysLoginDao;
import com.lt.base.model.SysLoginModel;
import com.lt.base.model.SysLoginRoleModel;
import com.lt.base.model.SysRoleModel;
import com.lt.base.service.SysLoginRoleService;
import com.lt.base.service.SysLoginService;
import com.lt.base.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-17
 * Time: 11:36
 */
@Service
public class SysLoginServiceImpl extends BaseServiceImpl<SysLoginModel> implements SysLoginService {
    @Resource
    private SysLoginDao dao;

    @Override
    public BaseDao<SysLoginModel> getBaseDao() {
        return dao;
    }

    @Override
    public SysLoginModel selectModelByUserName(String user_name) {
        return dao.selectModelByUserName(user_name);
    }

    @Override
    public SysLoginModel selectDetailsById(String id) {
        SysLoginModel sysLoginModel = dao.selectById(id);
        if (sysLoginModel != null) {
            return getLoginDetails(sysLoginModel);
        }
        return null;
    }

    @Override
    public List<SysLoginModel> selectListDetails(SysLoginModel model) {
        ArrayList<SysLoginModel> sysLoginModels = dao.selectListByModel(model);
        if (sysLoginModels != null) {
            //用于收集任务
            List<FutureTask<SysLoginModel>> futureTasks = new ArrayList<>();
            //创建线程池
            ExecutorService pool = Executors.newFixedThreadPool(5);
            for (SysLoginModel sysLoginModel : sysLoginModels) {
                FutureTask<SysLoginModel> futureTask =
                        new FutureTask<>(() -> getLoginDetails(sysLoginModel));
                pool.submit(futureTask);
                futureTasks.add(futureTask);
            }
            ArrayList<SysLoginModel> resultData = new ArrayList<>();
            for (FutureTask<SysLoginModel> futureTask : futureTasks) {
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


    @Resource
    private SysLoginRoleService sysLoginRoleService;

    @Resource
    private SysRoleService sysRoleService;

    private SysLoginModel getLoginDetails(SysLoginModel sysLoginModel) {
        String login_id = sysLoginModel.getId();
        List<SysLoginRoleModel> sysLoginRoleModels = sysLoginRoleService.selectByLoginId(login_id);
        if (sysLoginRoleModels != null && sysLoginRoleModels.size() > 0) {
            List<FutureTask<SysRoleModel>> futureTasks = new ArrayList<>();
            ArrayList<SysRoleModel> sysRoleModels = new ArrayList<>();
            ExecutorService pool = Executors.newFixedThreadPool(5);
            for (SysLoginRoleModel sysLoginRoleModel : sysLoginRoleModels) {
                FutureTask<SysRoleModel> futureTask = new FutureTask<>(() -> sysRoleService.selectDetailsById(sysLoginRoleModel.getRole_id()));
                pool.submit(futureTask);
                futureTasks.add(futureTask);
            }
            for (FutureTask<SysRoleModel> futureTask : futureTasks) {
                try {
                    SysRoleModel sysPermissionModel = futureTask.get();
                    sysRoleModels.add(sysPermissionModel);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            sysLoginModel.setRoles(sysRoleModels);
            //关闭线程池
            pool.shutdown();
        }
        return sysLoginModel;
    }


}
