package com.lt.body.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lt.base.constant.BaseConstant;
import com.lt.base.controller.BaseController;
import com.lt.body.business.model.UserModel;
import com.lt.body.business.service.UserService;
import com.lt.body.user.utils.JwtUtil;
import com.lt.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序调用
 */
@RestController
@Api(value = "微信授权登录", tags = {"微信授权登录"})
public class WeiXinXCXController extends BaseController {

    @Autowired
    private UserService userService;



    @ApiOperation("微信授权登录返回openId")
    @PostMapping("/api_p/ParkeUser/getOpenId")
    public HashMap getOpenId(@RequestParam(name = "code") String code) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String codeParams = "appid="+WechatConfig.appid +"&secret="+WechatConfig.secret+"&js_code=" + code +
                "&grant_type="+WechatConfig.grant_type;
        JSONObject jsonObject = JSONObject.parseObject(sendGet(url,codeParams));
        String openId = jsonObject.getString("openid");
        if (openId == null){
            return getReturnMap(BaseConstant.Response_MENU.REQUEST_OPENID_NOTFAILED);
        }
        String token = JwtUtil.createTokenWithClaim(openId);
        UserModel model = userService.selectById(openId);
        if (model == null){
            model = new UserModel();
            model.init(model);
            model.setId(openId);
            userService.insert(model);
        }else
        resultMap.put("openId",openId);
        return resultMap;
    }


    //发送GET请求:
    public static String  sendGet (String url,String param) {
        String result ="";
        BufferedReader in  =null;
        try {
            String urlNameString = url +"?" +param;
            System.out.println("发送的链接请求:"+urlNameString);
            URL reaurl = new URL(urlNameString);

            URLConnection connection  = reaurl.openConnection();
            //设置通用
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization", "5e79a44e0f459");
            //建立实际的连接
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            //定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
