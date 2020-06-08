package com.lt.body.user.controller;

import com.github.pagehelper.PageHelper;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.body.user.model.UserNotificationModel;
import com.lt.body.user.service.UserNotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;



@Api(value = "消息通知controller", tags = {"n消息通知操作接口"})

@RestController
@ApiIgnore
public class ApiUserNotificationController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------消息通知-----------------------start
    @Resource
    private UserNotificationService userNotificationService;


    @ApiOperation("消息通知-查询分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", required = true),
            @ApiImplicitParam(name = "pageSize", value = "数量"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
    })
    @ControllerMethodLog(remark = "消息通知-查询分页")
    @PostMapping("/api/UserNotification/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_UserNotification(HttpServletRequest request) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        UserNotificationModel userNotificationModel = new UserNotificationModel();
        userNotificationModel.getInstanceForSelect(request, userNotificationModel);
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        ArrayList list = userNotificationService.selectListByModel(userNotificationModel);

        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }




    //---------------------------消息通知-----------------------end
}
