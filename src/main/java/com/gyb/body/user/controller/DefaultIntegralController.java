package com.gyb.body.user.controller;

import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.controller.BaseController;
import com.gyb.base.poi.PoiExcelUtils;
import com.gyb.base.util.BeanRefUtil;
import com.gyb.body.user.model.UserIntegralModel;
import com.gyb.body.user.service.UserIntegralService;
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
public class DefaultIntegralController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------积分管理-----------------------start
    @Resource
    private UserIntegralService userIntegralService;
    @ControllerMethodLog(remark = "积分管理-插入")
    @PostMapping("/thirdPart/UserIntegral/insert.do")
    public HashMap insert_UserIntegral(HttpServletRequest request) {
        return insert(request, userIntegralService, new UserIntegralModel());
    }

    @ControllerMethodLog(remark = "积分管理-删除")
    @PostMapping("/thirdPart/UserIntegral/deleteById.do")
    public HashMap deleteById_UserIntegral(@RequestParam("id") String id) {
        return delete(userIntegralService, id);
    }

    @ControllerMethodLog(remark = "积分管理-更新")
    @PostMapping("/thirdPart/UserIntegral/updateById.do")
    public HashMap update_UserIntegral(HttpServletRequest request) {
        return update(request, userIntegralService, new UserIntegralModel());
    }

    @ControllerMethodLog(remark = "积分管理-查询一个")
    @PostMapping("/thirdPart/UserIntegral/selectById.do")
    public HashMap selectById_UserIntegral(@RequestParam("id") String id) {
        return selectById(userIntegralService, id);
    }

    @ControllerMethodLog(remark = "积分管理-查询多个")
    @PostMapping("/thirdPart/UserIntegral/selectAll.do")
    public HashMap selectList_UserIntegral(HttpServletRequest request) {
        return selectListByCondition(request, userIntegralService, new UserIntegralModel());
    }

    @ControllerMethodLog(remark = "积分管理-查询分页")
    @PostMapping("/thirdPart/UserIntegral/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_UserIntegral(HttpServletRequest request) {
        return selectListByPageHelper(request, userIntegralService, new UserIntegralModel());
    }

    @ControllerMethodLog(remark = "积分管理-批量导入-下载模版")
    @GetMapping("/thirdPart/UserIntegral/downloadTemplate.do")
    public void downloadTemplate_UserIntegral(HttpServletResponse response) throws IOException{
        PoiExcelUtils.doDownloadWorkbook("积分管理模版.xlsx", response, UserIntegralModel.class);
    }

    @ControllerMethodLog(remark = "积分管理-批量导入-导入")
    @PostMapping("/thirdPart/UserIntegral/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_UserIntegral(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, UserIntegralModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    UserIntegralModel baseModel = new UserIntegralModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    userIntegralService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------积分管理-----------------------end
}
