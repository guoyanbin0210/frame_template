package com.lt.body.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lt.base.dao.BaseDao;
import com.lt.base.service.impl.BaseServiceImpl;
import com.lt.body.weixin.dao.OrderDao;
import com.lt.body.weixin.model.OrderModel;
import com.lt.body.weixin.service.OrderService;
import com.lt.body.weixin.utils.OrderUtils;
import com.lt.body.weixin.utils.PayUtil;
import com.lt.config.WechatConfig;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * * Created with GuoYanBin.
 * Description:停车场订单 serviceImpl
 * Date: 2020-06-22
 * Time: 11:03
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class OrderServiceImpl extends BaseServiceImpl<OrderModel> implements OrderService {


    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    @Resource
    private OrderDao dao;


    @Override
    public BaseDao<OrderModel> getBaseDao() {
        return dao;
    }

    @Override
    public Integer insert(OrderModel orderModel){
        orderModel.setIsPay("0");//默认未支付
        orderModel.setStatus("1");//默认有效
        orderModel.setIsAppraise("0");
        orderModel.setOrderNo(OrderUtils.getOrderCode(orderModel.getUserId()));//订单编号
        return  dao.insert(orderModel);
    }

    @Override
    public  void updatePayment(String id){
        dao.updatePayment(id);
    }

    /**
     * 定时任务使用，更新订单为无效
     * @param parkId
     * @return
     */
    @Override
    public  Integer updateAfterNow(String parkId){
       return  dao.updateAfterNow(parkId);
    }

    /**
     * 更新为是否评价
     * @param orderNo
     * @return
     */
    @Override
    public Integer updateAppraise(String orderNo) {
        return dao.updateAppraise(orderNo);
    }


    @Override
    public OrderModel findDataByUserIdAndParkId(String userId, String parkId) {
        return dao.selectCountByUserIdAndParkId(userId,parkId);
    }


    /**
     * 微信小程序支付
     * @param spbill_create_ip
     * @param openId
     * @param orderNumber
     * @param price
     * @return
     */
    @Override
    public  Map wxPay(String spbill_create_ip, String openId, String orderNumber,int price) {
        Map<String, Object> payMap = new HashMap<String, Object>();//返回给小程序端需要的参数
        try {
            //生成的随机字符串
            String nonce_str = generateNonceStr();
            //商品名称
             String body = "密云静态停车";
            //int price = 100;//order.getAdjustPrice().multiply(￥).intValue();

            //组装参数，用户生成统一下单接口的签名
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


            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String sign = PayUtil.sign(prestr, WechatConfig.key, "utf-8").toUpperCase();

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>" + "<appid>" + WechatConfig.appid + "</appid>"
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
            System.out.println(xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WechatConfig.pay_url, "POST", xml);
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
                String paySign = PayUtil.sign(stringSignTemp, WechatConfig.key, "utf-8").toUpperCase();
                payMap.put("paySign", paySign);
            } else {
                return payMap;
            }
            //	OrderService.insert(payOperation);
            payMap.put("appid", WechatConfig.appid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
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
     *  微信支付成功回调后，更改订单支付状态
     * @param orderNo
     * @param amount
     * @return
     */
    @Override
    public Integer updatePaySuccess(String orderNo, BigDecimal amount,String payType) {
        return dao.updatePaySuccess(orderNo,amount,payType);
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
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String token = "Bearer 5e79a44e0f459";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("authorization", token);
            conn.setRequestProperty("accept", "application/json");
            //conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    @Override
    public OrderModel  selectByOrderNo(String orderNo){
        OrderModel model = dao.selectByOrderNo(orderNo);
        return model;
    }

}
