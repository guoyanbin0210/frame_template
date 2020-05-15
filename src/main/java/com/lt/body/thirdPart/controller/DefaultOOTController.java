package com.lt.body.thirdPart.controller;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.poi.PoiExcelUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.body.thirdPart.model.OttServicesModel;
import com.lt.body.thirdPart.service.OttServicesService;
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

/**
 * Created with GaoShan.
 * Description:
 * Date: 2019-05-17
 * Time: 09:27
 */
@RestController
@ApiIgnore
public class DefaultOOTController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------服务管理-----------------------start
    @Resource
    private OttServicesService ottServicesService;
    @ControllerMethodLog(remark = "服务管理-插入")
    @PostMapping("/thirdPart/OttServices/insert.do")
    public HashMap insert_OttServices(HttpServletRequest request) {
        return insert(request, ottServicesService, new OttServicesModel());
    }

    @ControllerMethodLog(remark = "服务管理-删除")
    @PostMapping("/thirdPart/OttServices/deleteById.do")
    public HashMap deleteById_OttServices(@RequestParam("id") String id) {
        return delete(ottServicesService, id);
    }

    @ControllerMethodLog(remark = "服务管理-更新")
    @PostMapping("/thirdPart/OttServices/updateById.do")
    public HashMap update_OttServices(HttpServletRequest request) {
        return update(request, ottServicesService, new OttServicesModel());
    }

    @ControllerMethodLog(remark = "服务管理-查询一个")
    @PostMapping("/thirdPart/OttServices/selectById.do")
    public HashMap selectById_OttServices(@RequestParam("id") String id) {
        return selectById(ottServicesService, id);
    }

    @ControllerMethodLog(remark = "服务管理-查询多个")
    @PostMapping("/thirdPart/OttServices/selectAll.do")
    public HashMap selectList_OttServices(HttpServletRequest request) {
        return selectListByCondition(request, ottServicesService, new OttServicesModel());
    }

    @ControllerMethodLog(remark = "服务管理-查询分页")
    @PostMapping("/thirdPart/OttServices/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_OttServices(HttpServletRequest request) {
        return selectListByPageHelper(request, ottServicesService, new OttServicesModel());
    }

    @ControllerMethodLog(remark = "服务管理-批量导入-下载模版")
    @GetMapping("/thirdPart/OttServices/downloadTemplate.do")
    public void downloadTemplate_OttServices(HttpServletResponse response) throws IOException{
        PoiExcelUtils.doDownloadWorkbook("服务管理模版.xlsx", response, OttServicesModel.class);
    }

    @ControllerMethodLog(remark = "服务管理-批量导入-导入")
    @PostMapping("/thirdPart/OttServices/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_OttServices(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, OttServicesModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    OttServicesModel baseModel = new OttServicesModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    ottServicesService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------服务管理-----------------------end
}
