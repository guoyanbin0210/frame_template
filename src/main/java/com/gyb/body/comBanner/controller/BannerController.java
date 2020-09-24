package com.gyb.body.comBanner.controller;
import com.github.pagehelper.PageHelper;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.comBanner.model.BannerModel;
import com.gyb.body.comBanner.service.BannerService;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
@ApiIgnore
public class BannerController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------banner管理-----------------------start
    @Resource
    private BannerService bannerService;
    @ControllerMethodLog(remark = "banner管理-插入")
    @PostMapping("/comBanner/Banner/insert.do")
    public HashMap insert_Banner(HttpServletRequest request) {
        return insert(request, bannerService, new BannerModel());
    }

    @ControllerMethodLog(remark = "banner管理-删除")
    @PostMapping("/comBanner/Banner/deleteById.do")
    public HashMap deleteById_Banner(@RequestParam("id") String id) {
        return delete(bannerService, id);
    }

    @ControllerMethodLog(remark = "banner管理-更新")
    @PostMapping("/comBanner/Banner/updateById.do")
    public HashMap update_Banner(HttpServletRequest request) {
        return update(request, bannerService, new BannerModel());
    }

    @ControllerMethodLog(remark = "banner管理-查询一个")
    @PostMapping("/comBanner/Banner/selectById.do")
    public HashMap selectById_Banner(@RequestParam("id") String id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        BannerModel baseModel = bannerService.selectById(id);
        if (baseModel != null) {
            getReturnMapSelect(resultMap, baseModel);
        }
        return resultMap;
    }

    @ControllerMethodLog(remark = "banner管理-查询多个")
    @PostMapping("/comBanner/Banner/selectAll.do")
    public HashMap selectList_Banner(HttpServletRequest request) {
        return selectListByCondition(request, bannerService, new BannerModel());
    }

    @ControllerMethodLog(remark = "banner管理-查询分页")
    @PostMapping("/comBanner/Banner/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_Banner(HttpServletRequest request) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        List<BannerModel> list = bannerService.findDataByHtml();
        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }
    //---------------------------banner管理-----------------------end
}
