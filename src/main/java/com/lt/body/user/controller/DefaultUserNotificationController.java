package com.lt.body.user.controller;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.poi.PoiExcelUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.body.user.model.UserNotificationModel;
import com.lt.body.user.service.UserNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@ApiIgnore
public class DefaultUserNotificationController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------消息通知-----------------------start
    @Resource
    private UserNotificationService userNotificationService;
    @ControllerMethodLog(remark = "消息通知-插入")
    @PostMapping("/user/UserNotification/insert.do")
    public HashMap insert_UserNotification(HttpServletRequest request) {
        return insert(request, userNotificationService, new UserNotificationModel());
    }

    @ControllerMethodLog(remark = "消息通知-删除")
    @PostMapping("/user/UserNotification/deleteById.do")
    public HashMap deleteById_UserNotification(@RequestParam("id") String id) {
        return delete(userNotificationService, id);
    }

    @ControllerMethodLog(remark = "消息通知-更新")
    @PostMapping("/user/UserNotification/updateById.do")
    public HashMap update_UserNotification(HttpServletRequest request) {
        return update(request, userNotificationService, new UserNotificationModel());
    }

    @ControllerMethodLog(remark = "消息通知-查询一个")
    @PostMapping("/user/UserNotification/selectById.do")
    public HashMap selectById_UserNotification(@RequestParam("id") String id) {
        return selectById(userNotificationService, id);
    }

    @ControllerMethodLog(remark = "消息通知-查询多个")
    @PostMapping("/user/UserNotification/selectAll.do")
    public HashMap selectList_UserNotification(HttpServletRequest request) {
        return selectListByCondition(request, userNotificationService, new UserNotificationModel());
    }

    @ControllerMethodLog(remark = "消息通知-查询分页")
    @PostMapping("/user/UserNotification/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_UserNotification(HttpServletRequest request) {
        return selectListByPageHelper(request, userNotificationService, new UserNotificationModel());
    }

    @ControllerMethodLog(remark = "消息通知-批量导入-下载模版")
    @GetMapping("/user/UserNotification/downloadTemplate.do")
    public void downloadTemplate_UserNotification(HttpServletResponse response) throws IOException{
        PoiExcelUtils.doDownloadWorkbook("消息通知模版.xlsx", response, UserNotificationModel.class);
    }

    @ControllerMethodLog(remark = "消息通知-批量导入-导入")
    @PostMapping("/user/UserNotification/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_UserNotification(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, UserNotificationModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    UserNotificationModel baseModel = new UserNotificationModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    userNotificationService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------消息通知-----------------------end
}
