package com.gyb.body.appManager.controller;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.controller.BaseController;
import com.gyb.body.appManager.model.AppManagerModel;
import com.gyb.body.appManager.service.AppManagerService;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@ApiIgnore
public class AppManagerController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------app版本更新-----------------------start
    @Resource
    private AppManagerService appManagerService;
    @ControllerMethodLog(remark = "app版本更新-插入")
    @PostMapping("/appManager/AppManager/insert.do")
    public HashMap insert_AppManager(HttpServletRequest request) {
        return insert(request, appManagerService, new AppManagerModel());
    }

    @ControllerMethodLog(remark = "app版本更新-删除")
    @PostMapping("/appManager/AppManager/deleteById.do")
    public HashMap deleteById_AppManager(@RequestParam("id") String id) {
        return delete(appManagerService, id);
    }

    @ControllerMethodLog(remark = "app版本更新-更新")
    @PostMapping("/appManager/AppManager/updateById.do")
    public HashMap update_AppManager(HttpServletRequest request) {
        return update(request, appManagerService, new AppManagerModel());
    }

    @ControllerMethodLog(remark = "app版本更新-查询一个")
    @PostMapping("/appManager/AppManager/selectById.do")
    public HashMap selectById_AppManager(@RequestParam("id") String id) {
        return selectById(appManagerService, id);
    }

    @ControllerMethodLog(remark = "app版本更新-查询多个")
    @PostMapping("/appManager/AppManager/selectAll.do")
    public HashMap selectList_AppManager(HttpServletRequest request) {
        return selectListByCondition(request, appManagerService, new AppManagerModel());
    }

    @ControllerMethodLog(remark = "app版本更新-查询分页")
    @PostMapping("/appManager/AppManager/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_AppManager(HttpServletRequest request) {
        return selectListByPageHelper(request, appManagerService, new AppManagerModel());
    }
    //---------------------------app版本更新-----------------------end
}
