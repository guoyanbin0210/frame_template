package com.lt.body.weixin.controller;

import com.lt.body.weixin.service.OrderService;
import com.lt.body.weixin.utils.PayUtil;
import com.lt.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@Api(value = "微信支付接口", tags = {"微信支付接口"})
public class WeiXinPayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api_p/pay/wxPay", method = RequestMethod.POST)
    @ApiOperation("微信支付")
    public String wxPay(String openId, String orderNumber ,int price ,HttpServletRequest request) {
        Object result = new Object();
        try {
            //获取自己的openId，可以找前端要
            // 	 String openId = "xxx";
            //订单号，自己的订单设计，最好用固定长度 20位等等。
            //  	 String orderNumber = "xxxx";
            //获取客户端的ip地址
            String spbill_create_ip = getIpAddr(request);
            //将支付业务下沉到service层
            result = orderService.wxPay(spbill_create_ip, openId, orderNumber ,price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PayUtil.toJson(result);
    }

    //这里是支付回调接口，微信支付成功后会自动调用
    @RequestMapping(value = "/api_p/pay/wxNotify", method = RequestMethod.POST)
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("微信返回给回调函数的信息为："+notityXml);
        Map map = PayUtil.doXMLParse(notityXml);

        if (map.get("return_code").equals("SUCCESS")  ) {
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String prestr = PayUtil.createLinkString(validParams);
            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (PayUtil.verify(prestr, (String) map.get("sign"), WechatConfig.key, "utf-8")) {

                /**此处添加自己的业务逻辑代码start**/
                String orderNo = (String) map.get("out_trade_no");// 商户订单号
                String amountpaid = (String)map.get("total_fee");// 实际支付的订单金额:单位 分
                BigDecimal amountPay = (new BigDecimal(amountpaid).divide(new BigDecimal("100"))).setScale(2);// 将分转换成元-实际支付金额:元

                orderService.updatePaySuccess(orderNo,amountPay,"微信支付");
                System.out.println("===notify===回调方法已经被调！！！");


                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


    //获取IP
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }




}
