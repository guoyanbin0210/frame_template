package com.gyb.body.apiController;

import com.github.pagehelper.PageHelper;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.apiMoudel.ApiContentCopyModel;
import com.gyb.body.apiMoudel.ApiContentModel;
import com.gyb.body.apiMoudel.ApiMenuModel;
import com.gyb.body.comContent.service.ContentService;
import com.gyb.body.comMenu.service.MenuService;
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
@Api(value = "信息接口 适用于解决方案、产品服务、经典案例", tags = {"信息接口 适用于解决方案、产品服务、经典案例"})
public class ApiContentController extends BaseController {

    @Resource
    private MenuService menuService;

    @Resource
    private ContentService contentService;

    @ApiOperation("根据导航栏id查询子菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parent_id", value = "一级导航栏id", required = true)
    })
    @PostMapping("/api_p/Comwinwin/selectMenuByParentId.do")
    public HashMap selectMenuByParentId(HttpServletRequest request,@RequestParam(name = "parent_id") String parent_id){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        List<ApiMenuModel> list = menuService.findDataByParentId(parent_id);
        if (list != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            resultMap.put("data",list);
        }
        return resultMap;
    }

    @ApiOperation("根据二级导航菜单id 查询数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu_id", value = "二级导航Id", required = true)
    })
    @PostMapping("/api_p/Comwinwin/selectMenuByMenuId.do")
    public HashMap selectMenuByMenuId(HttpServletRequest request,
                                      @RequestParam(name = "menu_id") String menu_id,
                                      @RequestParam(name = "pageSize") Integer pageSize,
                                      @RequestParam(name = "pageNum") Integer pageNum){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        PageHelper.startPage(pageNum, pageSize);
        List<ApiContentModel>  list = contentService.findDataByMenuId(menu_id);
        if (list != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            getReturnMapSelectByPage2(resultMap, list);
            return resultMap;
        }
        return resultMap;
    }

    @ApiOperation("根据菜单id查询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu_id", value = "菜单id", required = true)
    })
    @PostMapping("/api_p/Comwinwin/selectDataById.do")
    public HashMap selectDataById(HttpServletRequest request,@RequestParam(name = "menu_id") String menu_id){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        ApiContentCopyModel model = contentService.findDataById(menu_id);
        if (model != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            resultMap.put("data",model);
        }
        return resultMap;
    }




}
