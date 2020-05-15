package com.lt.body.user.controller;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.poi.PoiExcelUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.body.user.model.UserMainModel;
import com.lt.body.user.service.UserMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-05-10
 * Time: 03:31
 */
@RestController
@ApiIgnore
public class DefaultUserController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------用户信息-----------------------start
    @Resource
    private UserMainService userMainService;

    @ControllerMethodLog(remark = "用户信息-插入")
    @PostMapping("/user/UserMain/insert.do")
    public HashMap insert_UserMain(HttpServletRequest request) {
        return insert(request, userMainService, new UserMainModel());
    }

    @ControllerMethodLog(remark = "用户信息-删除")
    @PostMapping("/user/UserMain/deleteById.do")
    public HashMap deleteById_UserMain(@RequestParam("id") String id) {
        return delete(userMainService, id);
    }

    @ControllerMethodLog(remark = "用户信息-更新")
    @PostMapping("/user/UserMain/updateById.do")
    public HashMap update_UserMain(HttpServletRequest request) {
        return update(request, userMainService, new UserMainModel());
    }

    @ControllerMethodLog(remark = "用户信息-查询一个")
    @PostMapping("/user/UserMain/selectById.do")
    public HashMap selectById_UserMain(@RequestParam("id") String id) {
        return selectById(userMainService, id);
    }

    @ControllerMethodLog(remark = "用户信息-查询多个")
    @PostMapping("/user/UserMain/selectAll.do")
    public HashMap selectList_UserMain(HttpServletRequest request) {
        return selectListByCondition(request, userMainService, new UserMainModel());
    }

    @ControllerMethodLog(remark = "用户信息-查询分页")
    @PostMapping("/user/UserMain/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_UserMain(HttpServletRequest request) {
        return selectListByPageHelper(request, userMainService, new UserMainModel());
    }

    @ControllerMethodLog(remark = "用户信息-批量导入-下载模版")
    @GetMapping("/user/UserMain/downloadTemplate.do")
    public void downloadTemplate_UserMain(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("用户信息模版.xlsx", response, UserMainModel.class);
    }

    @ControllerMethodLog(remark = "用户信息-批量导入-导入")
    @PostMapping("/user/UserMain/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_UserMain(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, UserMainModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    UserMainModel baseModel = new UserMainModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    userMainService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------用户信息-----------------------end
}
