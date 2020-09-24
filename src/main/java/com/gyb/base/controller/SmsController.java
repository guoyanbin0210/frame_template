package com.gyb.base.controller;


import com.gyb.base.config.CodeUtils;
import com.gyb.base.constant.BaseConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class SmsController extends  BaseController{


    @Autowired
    private StringRedisTemplate template;

    @ApiOperation("获取短信验证码接口")
    @PostMapping("/api_p/sms/getCode")
    public HashMap getNoteCode(@RequestParam(name = "phone") String phone) {
        //  msgType 1 注册短信; 2 忘记密码 ; 3 验证码登录
        HashMap<String, Object> returnMap = getReturnMap();
        try {
            String check_code = CodeUtils.sendMsg(phone,"登录验证","SMS_68265062");
            if (check_code.equals("服务器内部错误")) {
                getReturnMap(BaseConstant.Response_MENU.REQUEST_USER_CODE_FAILED,returnMap);
                return returnMap;
            }
            //植入到redis中
            template.opsForValue().set("项目名称-"+phone, check_code, 5, TimeUnit.MINUTES);
            // String please = "短信验证码已发送到" + phone + "，请注意查收！";
            returnMap.put("check_code", check_code);
            return returnMap;
        } catch (Exception e) {
            return getReturnMap(BaseConstant.Response_MENU.REQUEST_SYSTEM_FAILED);
        }
    }

}
