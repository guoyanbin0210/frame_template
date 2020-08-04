package com.lt.body.business.controller;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.model.BaseModel;
import com.lt.body.business.model.OrderModel;
import com.lt.body.business.service.OrderService;
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
 * * Created with GuoYanBin.
 * Description:
 * Date: 2020-08-04
 * Time: 11:18
 */
@RestController
@ApiIgnore
public class OrderController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------信息-----------------------start
    @Resource
    private OrderService orderService;
    @ControllerMethodLog(remark = "信息-插入")
    @PostMapping("/business/Order/insert")
    public HashMap insert_Order(HttpServletRequest request) {
        return insert(request, orderService, new OrderModel());
    }

    @ControllerMethodLog(remark = "信息-删除")
    @PostMapping("/business/Order/deleteById")
    public HashMap deleteById_Order(@RequestParam("id") String id) {
        return delete(orderService, id);
    }

    @ControllerMethodLog(remark = "信息-更新")
    @PostMapping("/business/Order/updateById")
    public HashMap update_Order(HttpServletRequest request) {
        return update(request, orderService, new OrderModel());
    }

    @ControllerMethodLog(remark = "信息-查询一个")
    @PostMapping("/business/Order/selectById")
    public HashMap selectById_Order(@RequestParam("id") String id) {
        return selectById(orderService, id);
    }

    @ControllerMethodLog(remark = "信息-查询多个")
    @PostMapping("/business/Order/selectAll")
    public HashMap selectList_Order(HttpServletRequest request) {
        return selectListByCondition(request, orderService, new OrderModel());
    }

    @ControllerMethodLog(remark = "信息-查询分页")
    @PostMapping("/business/Order/selectListByPageHelper")
    public HashMap selectListByPageHelper_Order(HttpServletRequest request) {
        return selectListByPageHelper(request, orderService, new OrderModel());
    }
    //---------------------------信息-----------------------end
}
