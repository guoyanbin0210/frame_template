package com.lt.body.apiController;

import com.github.pagehelper.PageHelper;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.body.apiMoudel.ApiContentModel;
import com.lt.body.apiMoudel.ApiNewsMoudel;
import com.lt.body.newContent.service.NewContentService;
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
@Api(value = "新闻资讯 党建接口", tags = {"新闻资讯 党建接口"})
public class ApiNewsController extends BaseController {

    @Resource
    private NewContentService newContentService;

    @ApiOperation("根据菜单id 查询数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu_id", value = "菜单id", required = true)
    })
    @PostMapping("/api_p/Comwinwin/selectNewsByMenuId.do")
    public HashMap selectNewsByMenuId(HttpServletRequest request,
                                      @RequestParam(name = "menu_id") String menu_id,
                                      @RequestParam(name = "pageSize") Integer pageSize,
                                      @RequestParam(name = "pageNum") Integer pageNum){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        PageHelper.startPage(pageNum, pageSize);
        List<ApiNewsMoudel> list = newContentService.findDataByMenuId(menu_id);
        if (list != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            getReturnMapSelectByPage2(resultMap, list);
            return resultMap;
        }
        return resultMap;
    }

    @ApiOperation("根据Id查询详情")
    @PostMapping("/api_p/Comwinwin/selectNewsById.do")
    public HashMap selectNewsById(HttpServletRequest request,@RequestParam(name = "id") String id){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        ApiNewsMoudel model = newContentService.findDataById(id);
        if (model != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
           resultMap.put("data",model);
            return resultMap;
        }
        return resultMap;
    }

    @ApiOperation("查询首页新闻列表")
    @PostMapping("/api_p/Comwinwin/selectNewsByType.do")
    public HashMap selectNewsById(HttpServletRequest request,
                                  @RequestParam(name = "pageSize") Integer pageSize,
                                  @RequestParam(name = "pageNum") Integer pageNum){
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND);
        PageHelper.startPage(pageNum, pageSize);
        List<ApiNewsMoudel> list = newContentService.findDataByNewsType();
        if (list != null){
            resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
            getReturnMapSelectByPage2(resultMap, list);
            return resultMap;
        }
        return resultMap;
    }


}
