package com.gyb.body.business.controller;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.body.business.model.UserModel;
import com.gyb.body.business.service.UserService;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created with GuoYanBin.
 * Description:
 * Date: 2020-08-07
 * Time: 10:55
 */
@RestController
@ApiIgnore
public class UserController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------信息-----------------------start
    @Resource
    private UserService userService;



    @ControllerMethodLog(remark = "信息-插入")
    @PostMapping("/business/User/insert")
    public HashMap insert_User(HttpServletRequest request) {
        return insert(request, userService, new UserModel());
    }

    @ControllerMethodLog(remark = "信息-删除")
    @PostMapping("/business/User/deleteById")
    public HashMap deleteById_User(@RequestParam("id") String id) {
        return delete(userService, id);
    }

    @ControllerMethodLog(remark = "信息-更新")
    @PostMapping("/business/User/updateById")
    public HashMap update_User(HttpServletRequest request) {
        return update(request, userService, new UserModel());
    }

    @ControllerMethodLog(remark = "信息-查询一个")
    @PostMapping("/business/User/selectById")
    public HashMap selectById_User(@RequestParam("id") String id) {
        return selectById(userService, id);
    }

    @ControllerMethodLog(remark = "信息-查询多个")
    @PostMapping("/business/User/selectAll")
    public HashMap selectList_User(HttpServletRequest request) {
        return selectListByCondition(request, userService, new UserModel());
    }

    @ControllerMethodLog(remark = "信息-查询分页")
    @PostMapping("/business/User/selectListByPageHelper")
    public HashMap selectListByPageHelper_User(HttpServletRequest request) {
        return selectListByPageHelper(request, userService, new UserModel());
    }
    //---------------------------信息-----------------------end

    @ApiOperation("微信用 户名 头像存储 Update")
    @PostMapping("/api_p/User/setWXDetail")
    public HashMap setWXDetail(@RequestParam("openId") String openId,@RequestParam("wxUserName") String wxUserName,
                               @RequestParam("wxUserPic") String wxUserPic) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        try {
            UserModel userModel = userService.selectById(openId);
            if (userModel != null) {

                userService.update(userModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getReturnMap(BaseConstant.Response_MENU.REQUEST_SYSTEM_FAILED);
        }
        return resultMap;
    }
}
