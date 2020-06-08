package com.lt.base.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static Logger logger = LogManager.getLogger(HttpUtils.class.getName());

    public static String getOpenId(String js_code) {
        String appid = "";
        String secret = "";
        return getOpenId(js_code, appid, secret);

    }

    private static String getOpenId(String js_code, String appid, String secret) {
        try {
            String trim = ("https://api.weixin.qq.com/sns/jscode2session?appid=" +
                    appid +
                    "&secret=" +
                    secret +
                    "&js_code=" + js_code
                    + "&grant_type=authorization_code").trim();
            URL url = new URL(trim);
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                JSONObject jsonObject;
                jsonObject = getJsonObject(urlConnection);
                System.out.println(jsonObject.toString());
                Object openid = jsonObject.get("openid");
                if (openid != null) {
                    return (String) openid;
                }
            } else {
                logger.info("获取 openid 异常：" + urlConnection.getResponseCode());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getJsonObject(HttpURLConnection urlConnection) throws IOException {
        InputStream is = urlConnection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = is.read(buffer))) {
            baos.write(buffer, 0, len);
            baos.flush();
        }
        return new JSONObject(baos.toString("utf-8"));
    }

}
