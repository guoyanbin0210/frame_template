package com.lt.body.address.controller;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.controller.BaseController;
import com.lt.base.model.BaseModel;
import com.lt.body.address.model.AddressModel;
import com.lt.body.address.service.AddressService;
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
 * Date: 2020-03-02
 * Time: 09:02
 */
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
