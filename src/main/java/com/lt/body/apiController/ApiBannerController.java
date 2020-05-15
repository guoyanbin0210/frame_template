package com.lt.body.apiController;

import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.body.address.service.AddressService;
import com.lt.body.apiMoudel.ApiAddressMoudel;
import com.lt.body.apiMoudel.ApiBannerModel;
import com.lt.body.comBanner.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(value = "首页接口", tags = {"首页接口"})
public class ApiBannerController extends BaseController {

    @Resource
    private BannerService bannerService;

    @Resource
    private AddressService addressService;

    @ApiOperation("查询Banner、logo接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0:Banner 1:logo 2:新闻党建列表 3:新闻党建详情 4:列表页 5:详情页", required = true)
    })
    @ControllerMethodLog(remark = "根据type查询Banner图")
    @PostMapping("/api_p/Comwinwin/selectBannerType.do")
    public HashMap selectByMenu(HttpServletRequest request,@RequestParam(name = "type")String type){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        List<ApiBannerModel> list = bannerService.findDataByType(type);
        if (list.isEmpty()){
           return resultMap;
        }
        resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        resultMap.put("data",list);
        return resultMap;
    }

    @ApiOperation("查询底部信息接口")
    @PostMapping("/api_p/Comwinwin/selectAddress.do")
    public HashMap selectAddress(HttpServletRequest request){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        ApiAddressMoudel data = addressService.findData();
        if (data == null){
            return resultMap;
        }
        resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        resultMap.put("data",data);
        return resultMap;
    }

}
