package com.lt.body.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lt.body.weixin.model.TemplateDataVo;
import com.lt.body.weixin.model.WxMssVo;
import com.lt.body.weixin.utils.PayUtil;
import com.lt.body.weixin.utils.WeiXinUtils;
import com.lt.config.WechatConfig;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * * Created with GuoYanBin.
 * Description:  serviceImpl
 * Date: 2020-06-22
 * Time: 11:03
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class WeixinServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    private byte[] certData;
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 微信支付成功回调后，更改订单支付状态
     *
     * @param orderNo
     * @param amount
     * @return
     */
    public Integer updatePaySuccess(String orderNo, BigDecimal amount, String payType) {

        //订单表 修改为支付成功状态  orderStatus   1已付款，2 未付款失效，3已完成
     /*   String userId = "";
        String orderType = "";
        if (null != orderNo) {
            OrderModel oModel = orderService.getByOrderNo(orderNo);
            userId = oModel.getUserId();
            orderType = oModel.getOrderType();
            orderService.updatePaySuccess(orderNo, 3, "1");

        }


        if (!userId.equals("")) {
            UserModel userModel = userService.selectById(userId);
            BigDecimal userSurplusMoney = userModel.getSurplusMoney();
            BigDecimal surplusMoney = userSurplusMoney.subtract(amount);
            //用户表更新总余额
            if ("2".equals(orderType)) { //2代表充值
                Integer s = userService.updateUserMoney(userId, amount);
            }
            //消费记录表 增加一条收支记录
            ConsumeRecordsModel model = new ConsumeRecordsModel();
            model.init(model);
            model.setUserId(userId);
            model.setConsumeType(orderType);
            model.setConsumMoney(amount);
            if (surplusMoney.compareTo(BigDecimal.ZERO) >= 0) {
                model.setSurplusMoney(surplusMoney);
            }
            ConsumeRecordsService.insert(model);
        }*/
        return 1;
    }



    public  Map wxPay(String spbill_create_ip, String openId, String orderNumber,int price) {
        Map<String, Object> payMap = new HashMap<String, Object>();//返回给小程序端需要的参数
        try {
            //生成的随机字符串
            String nonce_str = generateNonceStr();
            //商品名称
            String body = "密云社区通";
            //int price = 100;//weixin.getAdjustPrice().multiply(￥).intValue();

            //组装参数，用户生成统一下单接口的签名
            logger.info("----------下单接口签名-------");
            Map<String, String> packageParams = new HashMap<>();

            packageParams.put("appid", WechatConfig.appid);
            packageParams.put("mch_id",  WechatConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no",orderNumber);
            packageParams.put("total_fee", price+""  );
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url",WechatConfig.notify_url);
            packageParams.put("trade_type", "JSAPI");
            packageParams.put("openid", openId);

            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            logger.info("----------生成签名字符串:" + prestr);

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String sign = PayUtil.sign(prestr, WechatConfig.secret, "utf-8").toUpperCase();
            logger.info("----------mysign:" + sign);
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>"
                    + "<appid>" + WechatConfig.appid + "</appid>"
                            + "<body><![CDATA[" + body + "]]></body>"
                            + "<mch_id>" + WechatConfig.mch_id + "</mch_id>"
                            + "<nonce_str>" + nonce_str + "</nonce_str>"
                            + "<notify_url>" +WechatConfig.notify_url + "</notify_url>"
                            + "<openid>" + openId + "</openid>"
                            + "<out_trade_no>" + orderNumber + "</out_trade_no>"
                            + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                            + "<total_fee>" + price + "</total_fee>"//支付的金额，单位：分
                            + "<trade_type>" + WechatConfig.TRADETYPE + "</trade_type>"
                            + "<sign>" + sign + "</sign>"
                        + "</xml>";
            System.out.println("xml= :" + xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WechatConfig.pay_url, "POST", xml);
            logger.info("----------result:" + result);
            System.out.println("支付接口返回报文"+result);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//返回状态码
            String err_code = (String) map.get("err_code");//返回状态码
            String err_code_des = (String) map.get("err_code_des");//返回状态码

            if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                payMap.put("nonceStr", nonce_str);
                payMap.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                payMap.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + WechatConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WechatConfig.secret, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：", paySign + "============ ======");
                payMap.put("paySign", paySign);
            } else {
                logger.info("----------统一下单失败-------");
                return payMap;
            }
            //	WeixinService.insert(payOperation);
            payMap.put("appid", WechatConfig.appid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
    }


    public Map wxRefund(String spbill_create_ip, String openId, String orderNumber, int price) {
        Map<String, Object> payMap = new HashMap<String, Object>();//返回给小程序端需要的参数
        try {
            //生成的随机字符串
            String nonce_str = generateNonceStr();
            //组装参数，用户生成统一下单接口的签名
            logger.info("----------退款接口签名-------");
            Map<String, String> packageParams = new HashMap<>();

            packageParams.put("appid", WechatConfig.appid);
            packageParams.put("mch_id", WechatConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("out_refund_no", orderNumber);
            packageParams.put("out_trade_no", orderNumber);
            packageParams.put("refund_fee", price + "");
            packageParams.put("total_fee", price + "");
            packageParams.put("refund_desc", "退款");
            packageParams.put("notify_url", WechatConfig.notify_refund_url);

            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            logger.info("----------生成签名字符串:" + prestr);
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String sign = PayUtil.sign(prestr, WechatConfig.secret, "utf-8").toUpperCase();
            packageParams.put("sign", sign);
            logger.info("----------mysign:" + sign);
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = PayUtil.map2XmlString(packageParams);
            //调用微信退款接口
            String s = doRefund(WechatConfig.refund_url, xml);
            payMap.put("msg", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
    }


    /**
     * 微信退款接口
     */
    private String doRefund(String url, String data) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12"); //证书格式
        try {
            InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("apiclient_cert.p12");
            this.certData = IOUtils.toByteArray(certStream);
            certStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayInputStream is = new ByteArrayInputStream(this.certData);
        try {
            keyStore.load(is, WechatConfig.mch_id.toCharArray());
        } finally {
            is.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(
                keyStore,
                WechatConfig.mch_id.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
        );
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }



    //获取随机字符串
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    /**
     * 发送post请求
     * @param url  路径
     * @param jsonObject  参数(json类型)
     * @param encoding 编码格式
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String send(String url, String jsonObject,String encoding) throws ParseException, IOException{
        String body = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        StringEntity s = new StringEntity(jsonObject, "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
        System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
            System.out.println(body);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * 发送post请求
     * @param url  路径
     * @param jsonObject  参数(json类型)
     * @param encoding 编码格式
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String sendPost(String url, JSONObject jsonObject,String encoding) throws ParseException, IOException{
        try {
            String body = "";

            //创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);

            //装填参数
            StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            //设置参数到请求对象中
            httpPost.setEntity(s);
            System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());

            //设置header信息
            //指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
            return body;
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
            return "1";
        } catch (IOException e) {
            e.printStackTrace();
            return "1";
        } catch (ParseException e) {
            e.printStackTrace();
            return "1";
        }
    }
    /**
     * @Description  消息推送
     * @param access_token  app的token
     * @param openid 用户openid
     * @param formId 表单ID
     * @param templateId 模板ID
     * @param keywords {与模板字段一一对应}
     * @return
     */
    public String pushOneUser(String access_token,String openid, String formId,String templateId,String[] keywords) {
        //如果access_token为空则从新获取
        if(StringUtils.isEmpty(access_token)){
            access_token = WeiXinUtils.getAccessToken();
        }
        String url = WechatConfig.message_send + access_token;

        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户openid
        wxMssVo.setForm_id(formId);//formId
        wxMssVo.setTemplate_id(templateId);//模版id
        Map<String, TemplateDataVo> m = new HashMap<>();

        //封装数据
        if(keywords.length>0){
            for(int i=1;i<=keywords.length;i++){
                TemplateDataVo keyword = new TemplateDataVo();
                keyword.setValue(keywords[i-1]);
                m.put("keyword"+i, keyword);
            }
            wxMssVo.setData(m);
        }else{
            logger.error("keywords长度为空");
            return null;
        }

        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        logger.error("小程序推送结果={}", responseEntity.getBody());
        return responseEntity.getBody();
    }
}
