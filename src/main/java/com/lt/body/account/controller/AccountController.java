package com.lt.body.account.controller;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.model.BaseModel;
import com.lt.body.account.model.AccountModel;
import com.lt.body.account.service.AccountService;
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
import java.util.HashMap;
import java.util.List;
/**
 * Created with GaoShan.
 * Description:
 * Date: 2020-02-20
 * Time: 02:21
 */
@RestController
@ApiIgnore
public class AccountController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------账号管理-----------------------start
    @Resource
    private AccountService accountService;
    @ControllerMethodLog(remark = "账号管理-插入")
    @PostMapping("/account/Account/insert.do")
    public HashMap insert_Account(HttpServletRequest request) {
        return insert(request, accountService, new AccountModel());
    }

    @ControllerMethodLog(remark = "账号管理-删除")
    @PostMapping("/account/Account/deleteById.do")
    public HashMap deleteById_Account(@RequestParam("id") String id) {
        return delete(accountService, id);
    }

    @ControllerMethodLog(remark = "账号管理-更新")
    @PostMapping("/account/Account/updateById.do")
    public HashMap update_Account(HttpServletRequest request) {
        return update(request, accountService, new AccountModel());
    }

    @ControllerMethodLog(remark = "账号管理-查询一个")
    @PostMapping("/account/Account/selectById.do")
    public HashMap selectById_Account(@RequestParam("id") String id) {
        return selectById(accountService, id);
    }

    @ControllerMethodLog(remark = "账号管理-查询多个")
    @PostMapping("/account/Account/selectAll.do")
    public HashMap selectList_Account(HttpServletRequest request) {
        return selectListByCondition(request, accountService, new AccountModel());
    }

    @ControllerMethodLog(remark = "账号管理-查询分页")
    @PostMapping("/account/Account/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_Account(HttpServletRequest request) {
        return selectListByPageHelper(request, accountService, new AccountModel());
    }
    //---------------------------账号管理-----------------------end
}
