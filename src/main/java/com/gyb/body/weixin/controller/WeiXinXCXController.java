package com.gyb.body.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.gyb.base.constant.BaseConstant;
import com.gyb.base.controller.BaseController;
import com.gyb.base.util.HttpUtils;
import com.gyb.body.business.model.UserModel;
import com.gyb.body.business.service.UserService;
import com.gyb.body.user.utils.JwtUtil;
import com.gyb.body.weixin.service.impl.WeixinServiceImpl;
import com.gyb.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序调用
 */
@RestController
@Api(value = "微信授权登录", tags = {"微信授权登录"})
public class WeiXinXCXController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private WeixinServiceImpl weixinService;

    @ApiOperation("微信小程序登录返回openId")
    @PostMapping("/api_p/Wechat/getOpenId")
    public HashMap getOpenId(@RequestParam(name = "code") String code) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String codeParams = "appid="+WechatConfig.appid +"&secret="+WechatConfig.secret+"&js_code=" + code +
                "&grant_type="+WechatConfig.grant_type;
        JSONObject jsonObject = JSONObject.parseObject(HttpUtils.sendGet(url,codeParams));
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



    public  void main(String[] args) {

        getminiqrQrTwo();
    }

    /**
     * 小程序二维码到本地  有效
     */
    public  void getminiqrQrTwo() {
        PrintWriter printWriter = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream swapStream = null;
        ByteArrayInputStream inputStream = null;
        OutputStream outputStream = null;
        String token  = weixinService.getAccess_token();
        try {
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", "");//参数
            paramJson.put("path", "pages/index");
            paramJson.put("width", 430);
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            bis = new BufferedInputStream(httpURLConnection.getInputStream());
            swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = bis.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            inputStream = new ByteArrayInputStream(swapStream.toByteArray());
            BufferedImage image = ImageIO.read(inputStream);
            BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), (int) (image.getHeight() * 0.85));
            BufferedImage inputbig = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = (Graphics2D) inputbig.getGraphics();
            g.drawImage(subImage, 0, 0, 256, 256, null);
            g.dispose();
            inputbig.flush();
            File file = new File("D:\\1.png");
            ImageIO.write(inputbig, "jpg", file);
        /*  outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (swapStream != null) {
                try {
                    swapStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 小程序二维码到本地 再到前端  无效
     */
    public static Map getminiqrQr(String sceneStr, String accessToken) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/index/index");
            param.put("width", 430);
            param.put("auto_color", false);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class,
                    new Object[0]);
            System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            File file = new File("D:/3.png");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] in_b  = null;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("调用异常");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }






    @ApiOperation("微信小程序推送测试")
    @PostMapping("/api_p/Wechat/messageSend")
    public void messageSend( ) {


        String meessage = weixinService.pushOneUser("", "");



    }


}
