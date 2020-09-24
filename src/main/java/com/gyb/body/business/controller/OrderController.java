package com.gyb.body.business.controller;
import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.controller.BaseController;
import com.gyb.body.business.model.OrderModel;
import com.gyb.body.business.service.OrderService;
import com.gyb.body.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    @Autowired
    private UserService userService;


    @PostMapping("/business/Order/test")
    public void test() throws InterruptedException {
        System.out.println("aaaaa");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5);
        userService.test();
        System.out.println(Thread.currentThread().getName());
        System.out.println("bbb");
        System.out.println(Thread.currentThread().getName());

    }



    @ControllerMethodLog(remark = "信息-插入")
    @PostMapping("/business/Order/insert")
    public HashMap insert_Order(HttpServletRequest request) {
        System.out.println("---");
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
