package com.gyb.body.newContent.controller;
import com.github.pagehelper.PageHelper;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.newContent.model.NewContentModel;
import com.gyb.body.newContent.service.NewContentService;
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
public class NewContentController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------新闻管理-----------------------start
    @Resource
    private NewContentService newContentService;
    @ControllerMethodLog(remark = "新闻管理-插入")
    @PostMapping("/newContent/NewContent/insert.do")
    public HashMap insert_NewContent(HttpServletRequest request) {
        return insert(request, newContentService, new NewContentModel());
    }

    @ControllerMethodLog(remark = "新闻管理-删除")
    @PostMapping("/newContent/NewContent/deleteById.do")
    public HashMap deleteById_NewContent(@RequestParam("id") String id) {
        return delete(newContentService, id);
    }

    @ControllerMethodLog(remark = "新闻管理-更新")
    @PostMapping("/newContent/NewContent/updateById.do")
    public HashMap update_NewContent(HttpServletRequest request) {
        return update(request, newContentService, new NewContentModel());
    }

    @ControllerMethodLog(remark = "新闻管理-查询一个")
    @PostMapping("/newContent/NewContent/selectById.do")
    public HashMap selectById_NewContent(@RequestParam("id") String id) {
        return selectById(newContentService, id);
    }

    @ControllerMethodLog(remark = "新闻管理-查询多个")
    @PostMapping("/newContent/NewContent/selectAll.do")
    public HashMap selectList_NewContent(HttpServletRequest request) {
        return selectListByCondition(request, newContentService, new NewContentModel());
    }

    @ControllerMethodLog(remark = "新闻管理-查询分页")
    @PostMapping("/newContent/NewContent/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_NewContent(HttpServletRequest request) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        String keyword = request.getParameter("keyWord");
        String menu_id = request.getParameter("menu_id");
        String parent_id = request.getParameter("parent_id");
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        List<NewContentModel> list = newContentService.findDataByHtml(menu_id,parent_id,keyword);
        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }
    //---------------------------新闻管理-----------------------end
}
