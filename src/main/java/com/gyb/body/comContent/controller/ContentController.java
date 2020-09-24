package com.gyb.body.comContent.controller;
import com.github.pagehelper.PageHelper;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.comContent.model.ContentModel;
import com.gyb.body.comContent.service.ContentService;
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
public class ContentController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------文章管理-----------------------start
    @Resource
    private ContentService contentService;
    @ControllerMethodLog(remark = "文章管理-插入")
    @PostMapping("/comContent/Content/insert.do")
    public HashMap insert_Content(HttpServletRequest request) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        ContentModel model = new ContentModel();
        model.getInstanceForInsert(request, model);
        //检查文章是否存在
        Integer rows = contentService.findCountByParentId(model.getTb_parent_id());
        if (rows >= 1){
            returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_CONTENT);
            return  returnMap;
        }
        int insert = contentService.insert(model);
        if (insert != 0) {
            returnMap.put("data", model);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;

    }

    @ControllerMethodLog(remark = "文章管理-删除")
    @PostMapping("/comContent/Content/deleteById.do")
    public HashMap deleteById_Content(@RequestParam("id") String id) {
        return delete(contentService, id);
    }

    @ControllerMethodLog(remark = "文章管理-更新")
    @PostMapping("/comContent/Content/updateById.do")
    public HashMap update_Content(HttpServletRequest request) {
        return update(request, contentService, new ContentModel());
    }

    @ControllerMethodLog(remark = "文章管理-查询一个")
    @PostMapping("/comContent/Content/selectById.do")
    public HashMap selectById_Content(@RequestParam("id") String id) {
        return selectById(contentService, id);
    }

    @ControllerMethodLog(remark = "文章管理-查询多个")
    @PostMapping("/comContent/Content/selectAll.do")
    public HashMap selectList_Content(HttpServletRequest request) {
        return selectListByCondition(request, contentService, new ContentModel());
    }

    @ControllerMethodLog(remark = "文章管理-查询分页")
    @PostMapping("/comContent/Content/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_Content(HttpServletRequest request) {

        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        ContentModel model = new ContentModel();
        model.getInstanceForSelect(request, model);
        String keyword = request.getParameter("keyWord");
        String id = request.getParameter("id");
        String tb_menu_id = request.getParameter("tb_menu_id");
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);

        List<ContentModel> list = contentService.findDataByHtml(tb_menu_id,id,keyword);

        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }
    //---------------------------文章管理-----------------------end

}
