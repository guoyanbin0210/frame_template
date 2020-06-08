package com.lt.body.user.controller;

import com.lt.base.controller.BaseController;
import com.lt.base.service.BaseDirService;
import com.lt.body.user.service.UserIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;



@RestController
@ApiIgnore
public class ByIntegralController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------积分管理-----------------------start
    @Resource
    private UserIntegralService userIntegralService;

    @Resource
    private BaseDirService baseDirService;




////    @ApiOperation("积分-查询积分规则")
////    @ApiImplicitParams({})
//    @ControllerMethodLog(remark = "积分-查询积分规则")
//    @PostMapping("/bg/BaseDir/select_INTEGRAL_RULE.do")
//    public HashMap selectList_WENXZHENG_QUE_TYPE(HttpServletRequest request) {
//        BaseDirModel baseDirModel = new BaseDirModel();
//        baseDirModel.setBd_type(DirEnum.INTEGRAL_RULE.getCode());
//        baseDirModel.setBase_available(1);
//        return selectListByCondition(request, baseDirService, baseDirModel);
//    }
//
//
//    ////////////////////////////////////////
//    @ControllerMethodLog(remark = "积分-添加规则")
//    @PostMapping("/bg/BaseDir/insert_INTEGRAL_RULE.do")
//    public HashMap insert_INTEGRAL_RULE(HttpServletRequest request
//            , @RequestParam("bd_name") String bd_name//名称
//            , @RequestParam("bd_des") String bd_des
//            , @RequestParam("bd_data") String bd_data //分值
//            , @RequestParam("bd_other") String bd_other //上限
//    ) {
//
//
//        return getAddBaseDirHashMap(request, bd_name, "该规则已经存在", DirEnum.WENXZHENG_QUE_TYPE);
//    }
//    private HashMap getAddBaseDirHashMap(HttpServletRequest request, @RequestParam("bd_name") String bd_name, String message, DirEnum wenxzhengQueType) {
//        HashMap<String, Object> returnMap = getReturnMap();
//        BaseDirModel baseDirModel1 = baseDirService.selectOneByName(bd_name);
//        if (baseDirModel1 != null) {
//            returnMap.put("message", message);
//            return returnMap;
//        }
//        BaseDirModel baseDirModel = new BaseDirModel();
//        baseDirModel.getInstanceForInsert(request, baseDirModel);
//        baseDirModel.setBd_type(wenxzhengQueType.getCode());
//
//        Integer insert = baseDirService.insert(baseDirModel);
//        if (insert != 0) {
//            getReturnMapSuccess(returnMap);
//        }
//        return returnMap;
//    }

    //---------------------------积分管理-----------------------end
}
