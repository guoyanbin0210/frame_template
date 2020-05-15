package com.lt.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lt.base.constant.BaseConstant;
import com.lt.base.model.BaseDirModel;
import com.lt.base.model.BaseModel;
import com.lt.base.model.SysLoginModel;
import com.lt.base.service.BaseDirService;
import com.lt.base.service.BaseService;
import com.lt.base.util.BaseUtils;
import com.lt.body.user.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-11-26
 * Time: 17:10
 */
public class BaseController {



    private final Logger logger = LogManager.getLogger(getClass());
    protected void printlnEnumeration(HttpServletRequest request) {
        Enumeration<String> em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            String value = request.getParameter(name);
            logger.info("name = " + name + ",value = " + value);
        }
    }
    /**
     * @return 默认返回操作失败
     */
    protected HashMap<String, Object> getReturnMap() {
        return getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_FAILED);
    }
    /**
     * @param returnMap 默认返回操作成功
     */
    protected void getReturnMapSuccess(HashMap<String, Object> returnMap) {
        getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
    }
    protected HashMap<String, Object> getReturnMap(BaseConstant.Response_MENU responseMENU) {
        HashMap<String, Object> returnMap = new LinkedHashMap<>();
        returnMap.put("status", responseMENU.getCode());
        returnMap.put("message", responseMENU.getDir());
        return returnMap;
    }
    public void getReturnMap(BaseConstant.Response_MENU responseMENU, HashMap<String, Object> returnMap) {
        returnMap.put("status", responseMENU.getCode());
        returnMap.put("message", responseMENU.getDir());
    }


    /**
     * 针对返回多条数据
     */
    protected void getReturnMapSelect(HashMap<String, Object> returnMap, List list) {
        if (list != null) {
            if (list.size() == 0) {
                getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND, returnMap);
                return;
            }
            returnMap.put("data", list);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
    }

    /**
     * 针对返回多条数据
     */
    protected void getReturnMapSelectByPage(HashMap<String, Object> returnMap, List list) {
        if (list != null) {
            if (list.size() == 0) {
                getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND, returnMap);
                return;
            }

            getReturnMapSelectByPage2(returnMap, list);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);

        }
    }

    public void getReturnMapSelectByPage2(HashMap<String, Object> returnMap, List list) {
        PageInfo page = new PageInfo<>(list);
        HashMap<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", page.getPageNum());
        pageInfo.put("pageSize", page.getPageSize());
        pageInfo.put("startRow", page.getStartRow());
        pageInfo.put("endRow", page.getEndRow());
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("navigateFirstPage", page.getNavigateFirstPage());
        pageInfo.put("navigateLastPage", page.getNavigateLastPage());
        pageInfo.put("isFirstPage", page.isIsFirstPage());
        pageInfo.put("isLastPage", page.isIsLastPage());
        pageInfo.put("hasPreviousPage", page.isHasPreviousPage());
        pageInfo.put("hasNextPage", page.isHasNextPage());
        returnMap.put("pageInfo", pageInfo);
        returnMap.put("data", list);
    }


    /**
     * 针对返回一条数据
     */
    protected void getReturnMapSelect(HashMap<String, Object> returnMap, Object data) {
        if (data != null) {
            returnMap.put("data", data);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        } else {
            getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_NOT_FOUND, returnMap);
        }
    }

    /**
     * 上传
     */
    protected void getReturnMapUpload(HashMap<String, Object> returnMap) {
        getReturnMap(BaseConstant.Response_MENU.REQUEST_UPLOAD_FAILED, returnMap);
    }

    /**
     * 下载
     */
    protected void getReturnMapDownload(HashMap<String, Object> returnMap) {
        getReturnMap(BaseConstant.Response_MENU.REQUEST_DOWNLOAD_FAILED, returnMap);
    }

    protected HashMap<String, Object> insert(HttpServletRequest request, BaseService service, BaseModel baseModel) {
        baseModel.getInstanceForInsert(request, baseModel);
        return insert(service, baseModel);
    }

    protected HashMap<String, Object> insert(BaseService service, BaseModel baseModel) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        int insert = service.insert(baseModel);
        if (insert != 0) {
            returnMap.put("data", baseModel);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;
    }

    protected HashMap<String, Object> delete(BaseService service, String id) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DELETE_FAILED);
        int delete = service.delete(id);
        if (delete != 0) {
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;
    }

    protected HashMap<String, Object> update(HttpServletRequest request, BaseService service, BaseModel baseModel) {
        baseModel.getInstanceForUpdate(request, baseModel);
        return update(service, baseModel);
    }

    protected HashMap<String, Object> update(BaseService service, BaseModel baseModel) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_UPDATE_FAILED);
        int update = service.update(baseModel);
        if (update != 0) {
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;
    }

    protected HashMap<String, Object> selectById(BaseService service, String id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        Object baseModel = service.selectById(id);
        if (baseModel != null) {
            getReturnMapSelect(resultMap, baseModel);
        }
        return resultMap;
    }

    protected HashMap<String, Object> selectAll(BaseService service, String id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        Object baseModel = service.selectAll();
        if (baseModel != null) {
            getReturnMapSelect(resultMap, baseModel);
        }
        return resultMap;
    }

    protected HashMap<String, Object> selectListByCondition(HttpServletRequest request, BaseService service, BaseModel baseModel) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        baseModel.getInstanceForSelect(request, baseModel);
        ArrayList listByCondition = service.selectListByModel(baseModel);
        getReturnMapSelect(resultMap, listByCondition);
        return resultMap;
    }

    protected HashMap<String, Object> selectListByPageHelper(HttpServletRequest request, BaseService service, BaseModel baseModel) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        baseModel.getInstanceForSelect(request, baseModel);
        String keyword = request.getParameter("keyWord");
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        ArrayList list;
        if (StringUtils.isEmpty(keyword)) {
            list = service.selectListByModel(baseModel);
        } else {
            list = service.selectListByKeyWord(keyword);
        }
        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }
    //获取用户名
    protected String getUserName(){
        return BaseUtils.getCurrLoginModel().getUser_name();
    }
    //转换日期
    protected String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    //获取token 用户
    protected String getTokenUser(HttpServletRequest request){
        return JwtUtil.verifyToken(request.getHeader("Authentication"));
    }

    protected String checkString(String str){
        if (str == null){
            return "";
        }
        return str;
    }


    /**
     * 获取页码,获取页条目总数
     * int[] pageNS = MyPageUtil.getPageStringToIntArray(request);
     * PageHelper.startPage(pageNS[0], pageNS[1]);
     */
    public static int[] getPageStringToIntArray(HttpServletRequest request) {
        String s_pageNum = request.getParameter("pageNum");
        String s_pageSize = request.getParameter("pageSize");
        int pageNum = 1;
        int pageSize = 10;
        int isPages = 0;
        if (StringUtil.isNotEmpty(s_pageNum)) {
            pageNum = Integer.parseInt(s_pageNum);
            isPages = 1;
        }
        if (StringUtil.isNotEmpty(s_pageSize)) {
            pageSize = Integer.parseInt(s_pageSize);
            isPages = 1;
        }
        int[] arr = new int[3];
        arr[0] = pageNum;
        arr[1] = pageSize;
        arr[2] = isPages;
        return arr;
    }

}
