package com.lt.body.user.controller;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.poi.PoiExcelUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.body.user.model.IntegralRuleModel;
import com.lt.body.user.service.IntegralRuleService;
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
 * Date: 2019-07-02
 * Time: 03:27
 */
@RestController
@ApiIgnore
public class DefaultIntegralRuleController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------积分规则-----------------------start
    @Resource
    private IntegralRuleService integralRuleService;
    @ControllerMethodLog(remark = "积分规则-插入")
    @PostMapping("/user/IntegralRule/insert.do")
    public HashMap insert_IntegralRule(HttpServletRequest request) {
        return insert(request, integralRuleService, new IntegralRuleModel());
    }

    @ControllerMethodLog(remark = "积分规则-删除")
    @PostMapping("/user/IntegralRule/deleteById.do")
    public HashMap deleteById_IntegralRule(@RequestParam("id") String id) {
        return delete(integralRuleService, id);
    }

    @ControllerMethodLog(remark = "积分规则-更新")
    @PostMapping("/user/IntegralRule/updateById.do")
    public HashMap update_IntegralRule(HttpServletRequest request) {
        return update(request, integralRuleService, new IntegralRuleModel());
    }

    @ControllerMethodLog(remark = "积分规则-查询一个")
    @PostMapping("/user/IntegralRule/selectById.do")
    public HashMap selectById_IntegralRule(@RequestParam("id") String id) {
        return selectById(integralRuleService, id);
    }

    @ControllerMethodLog(remark = "积分规则-查询多个")
    @PostMapping("/user/IntegralRule/selectAll.do")
    public HashMap selectList_IntegralRule(HttpServletRequest request) {
        return selectListByCondition(request, integralRuleService, new IntegralRuleModel());
    }

    @ControllerMethodLog(remark = "积分规则-查询分页")
    @PostMapping("/user/IntegralRule/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_IntegralRule(HttpServletRequest request) {
        return selectListByPageHelper(request, integralRuleService, new IntegralRuleModel());
    }

    @ControllerMethodLog(remark = "积分规则-批量导入-下载模版")
    @GetMapping("/user/IntegralRule/downloadTemplate.do")
    public void downloadTemplate_IntegralRule(HttpServletResponse response) throws IOException{
        PoiExcelUtils.doDownloadWorkbook("积分规则模版.xlsx", response, IntegralRuleModel.class);
    }

    @ControllerMethodLog(remark = "积分规则-批量导入-导入")
    @PostMapping("/user/IntegralRule/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_IntegralRule(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, IntegralRuleModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    IntegralRuleModel baseModel = new IntegralRuleModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    integralRuleService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------积分规则-----------------------end
}
