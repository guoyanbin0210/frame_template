package com.lt.body.aboutUs.controller;
import com.github.pagehelper.PageHelper;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.base.model.BaseModel;
import com.lt.body.aboutUs.model.AboutUsModel;
import com.lt.body.aboutUs.service.AboutUsService;
import io.swagger.models.auth.In;
import org.springframework.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;
import com.lt.base.model.SysLoginModel;
import com.lt.base.service.SysLoginService;
import com.lt.base.util.BeanRefUtil;
import com.lt.base.poi.PoiExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@ApiIgnore
public class AboutUsController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------关于我们-----------------------start
    @Resource
    private AboutUsService aboutUsService;
    @ControllerMethodLog(remark = "关于我们-插入")
    @PostMapping("/aboutUs/AboutUs/insert.do")
    public HashMap insert_AboutUs(HttpServletRequest request) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        AboutUsModel model = new AboutUsModel();
        model.getInstanceForInsert(request, model);
        Integer rows = aboutUsService.findDataByMenuId(model.getMenu_id());
        if (rows >= 1){
            returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_CONTENT);
            return  returnMap;
        }
        int insert = aboutUsService.insert(model);
        if (insert != 0) {
            returnMap.put("data", model);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;
    }

    @ControllerMethodLog(remark = "关于我们-删除")
    @PostMapping("/aboutUs/AboutUs/deleteById.do")
    public HashMap deleteById_AboutUs(@RequestParam("id") String id) {
        return delete(aboutUsService, id);
    }

    @ControllerMethodLog(remark = "关于我们-更新")
    @PostMapping("/aboutUs/AboutUs/updateById.do")
    public HashMap update_AboutUs(HttpServletRequest request) {
        return update(request, aboutUsService, new AboutUsModel());
    }

    @ControllerMethodLog(remark = "关于我们-查询一个")
    @PostMapping("/aboutUs/AboutUs/selectById.do")
    public HashMap selectById_AboutUs(@RequestParam("id") String id) {
        return selectById(aboutUsService, id);
    }

    @ControllerMethodLog(remark = "关于我们-查询多个")
    @PostMapping("/aboutUs/AboutUs/selectAll.do")
    public HashMap selectList_AboutUs(HttpServletRequest request) {
        return selectListByCondition(request, aboutUsService, new AboutUsModel());
    }

    @ControllerMethodLog(remark = "关于我们-查询分页")
    @PostMapping("/aboutUs/AboutUs/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_AboutUs(HttpServletRequest request) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        String keyword = request.getParameter("keyWord");
        String menu_id = request.getParameter("menu_id");
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        List<AboutUsModel> list = aboutUsService.findDataByHtml(menu_id,keyword);
        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }
    //---------------------------关于我们-----------------------end
}
