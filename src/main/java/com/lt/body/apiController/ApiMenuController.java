package com.lt.body.apiController;

import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.body.apiMoudel.ApiMenuModel;
import com.lt.body.comMenu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
@RestController
@Api(value = "导航栏接口", tags = {"导航栏接口"})
public class ApiMenuController  extends BaseController {

    @Resource
    private MenuService menuService;

    @ApiOperation("查询菜单接口")
    @PostMapping("/api_p/Comwinwin/selectMenu.do")
    public HashMap selectByMenu(HttpServletRequest request){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        List<ApiMenuModel> list = menuService.findDataByMenu();
        if (list != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            resultMap.put("data",list);
        }
        return resultMap;
    }
}
