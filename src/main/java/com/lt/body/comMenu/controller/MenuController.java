package com.lt.body.comMenu.controller;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.base.model.BaseModel;
import com.lt.body.comMenu.model.MenuModel;
import com.lt.body.comMenu.service.MenuService;
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
/**
 * Created with GaoShan.
 * Description:
 * Date: 2020-02-27
 * Time: 10:47
 */
@RestController
@ApiIgnore
public class MenuController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------菜单管理-----------------------start
    @Resource
    private MenuService menuService;
    @ControllerMethodLog(remark = "菜单管理-插入")
    @PostMapping("/comMenu/Menu/insert.do")
    public HashMap insert_Menu(HttpServletRequest request) {
        MenuModel model = new MenuModel();
        model.getInstanceForInsert(request, model);
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        int insert = menuService.insert(model);
        if (insert != 0) {
            returnMap.put("data", model);
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }

    @ControllerMethodLog(remark = "菜单管理-删除")
    @PostMapping("/comMenu/Menu/deleteById.do")
    public HashMap deleteById_Menu(@RequestParam("id") String id) {
        return delete(menuService, id);
    }

    @ControllerMethodLog(remark = "菜单管理-更新")
    @PostMapping("/comMenu/Menu/updateById.do")
    public HashMap update_Menu(HttpServletRequest request) {

        return update(request, menuService, new MenuModel());
    }

    @ControllerMethodLog(remark = "菜单管理-查询一个")
    @PostMapping("/comMenu/Menu/selectById.do")
    public HashMap selectById_Menu(@RequestParam("id") String id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        MenuModel baseModel = menuService.selectById(id);
        if (baseModel != null) {
            getReturnMapSelect(resultMap, baseModel);
        }
        return resultMap;
    }

    @ControllerMethodLog(remark = "菜单管理-查询多个")
    @PostMapping("/comMenu/Menu/selectAll.do")
    public ArrayList selectList_Menu(HttpServletRequest request) {
        ArrayList<MenuModel> list = menuService.selectAll();
        return list;
    }

    @ControllerMethodLog(remark = "菜单管理-查询分页")
    @PostMapping("/comMenu/Menu/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_Menu(HttpServletRequest request) {
        return selectListByPageHelper(request, menuService, new MenuModel());
    }
    //---------------------------菜单管理-----------------------end

    @ControllerMethodLog(remark = "查询菜单")
    @PostMapping("/comMenu/Menu/selectListByMenu.do")
    public HashMap selectListByMenu(HttpServletRequest request,String type) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        List<MenuModel> list = menuService.findDataByType(type);
        returnMap.put("data",list);
        return returnMap;
    }
    @ControllerMethodLog(remark = "查询子集菜单")
    @PostMapping("/comMenu/Menu/selectListByMenuId.do")
    public HashMap selectListByMenuId(HttpServletRequest request,String id,String type) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        List<MenuModel> list = menuService.findDataById(id,type);
        returnMap.put("data",list);
        return returnMap;
    }


}
