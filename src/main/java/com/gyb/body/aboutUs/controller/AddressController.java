package com.gyb.body.aboutUs.controller;

import com.gyb.base.aop.ControllerMethodLog;
import com.gyb.base.controller.BaseController;
import com.gyb.body.aboutUs.model.AddressModel;
import com.gyb.body.aboutUs.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@ApiIgnore
public class AddressController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    //---------------------------底部信息-----------------------start
    @Resource
    private AddressService addressService;
    @ControllerMethodLog(remark = "底部信息-插入")
    @PostMapping("/address/Address/insert.do")
    public HashMap insert_Address(HttpServletRequest request) {
        return insert(request, addressService, new AddressModel());
    }

    @ControllerMethodLog(remark = "底部信息-删除")
    @PostMapping("/address/Address/deleteById.do")
    public HashMap deleteById_Address(@RequestParam("id") String id) {
        return delete(addressService, id);
    }

    @ControllerMethodLog(remark = "底部信息-更新")
    @PostMapping("/address/Address/updateById.do")
    public HashMap update_Address(HttpServletRequest request) {
        return update(request, addressService, new AddressModel());
    }

    @ControllerMethodLog(remark = "底部信息-查询一个")
    @PostMapping("/address/Address/selectById.do")
    public HashMap selectById_Address(@RequestParam("id") String id) {
        return selectById(addressService, id);
    }

    @ControllerMethodLog(remark = "底部信息-查询多个")
    @PostMapping("/address/Address/selectAll.do")
    public HashMap selectList_Address(HttpServletRequest request) {
        return selectListByCondition(request, addressService, new AddressModel());
    }

    @ControllerMethodLog(remark = "底部信息-查询分页")
    @PostMapping("/address/Address/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_Address(HttpServletRequest request) {
        return selectListByPageHelper(request, addressService, new AddressModel());
    }
    //---------------------------底部信息-----------------------end
}
