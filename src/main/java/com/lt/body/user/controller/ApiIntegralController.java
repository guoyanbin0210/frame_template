package com.lt.body.user.controller;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.body.user.service.IntegralRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;



@RestController
@Api(value = "m积分", tags = {"m积分操作接口"})
@ApiIgnore
public class ApiIntegralController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------积分管理-----------------------start

    @Resource
    private IntegralRuleService integralRuleService;

    @ApiOperation(value = "积分管理-积分规则")
    @ApiImplicitParams({
    })
    @ControllerMethodLog(remark = "积分管理-积分规则")
    @PostMapping("/api_p/UserIntegral/selectAll.do")
    public HashMap selectListByPageHelper_UserIntegral(HttpServletRequest request) {
        HashMap<String, Object> returnMap = getReturnMap();
        List userIntegralModels = integralRuleService.selectAll();
        getReturnMapSelect(returnMap, userIntegralModels);
        return returnMap;
    }

    //---------------------------积分管理-----------------------end
}
