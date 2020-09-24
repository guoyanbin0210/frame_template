package com.gyb.body.apiController;

import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.aboutUs.service.AboutUsService;
import com.gyb.body.apiMoudel.ApiAboutMoudel;
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

@RestController
@Api(value = "关于我们接口", tags = {"关于我们接口"})
public class ApiAboutController extends BaseController {

    @Resource
    private AboutUsService aboutUsService;

    @ApiOperation("根据菜单id查询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu_id", value = "菜单id", required = true)
    })
    @PostMapping("/api_p/Comwinwin/selectAboutDataById.do")
    public HashMap selectAboutDataById(HttpServletRequest request, @RequestParam(name = "menu_id") String menu_id){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        ApiAboutMoudel model = aboutUsService.findDataById(menu_id);
        if (model != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            resultMap.put("data",model);
        }
        return resultMap;
    }

}
