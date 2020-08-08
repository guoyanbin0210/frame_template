package com.lt.body.weixin.controller;

import com.lt.base.controller.BaseController;
import com.lt.body.weixin.service.impl.WeixinServiceImpl;
import com.lt.body.weixin.utils.PayUtil;
import com.lt.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "微信支付接口", tags = {"微信支付接口"})
public class WeiXinPayController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(WeiXinPayController.class);

    @Autowired
    private WeixinServiceImpl weixinService;

    @RequestMapping(value = "/api_p/pay/wxPay", method = RequestMethod.POST)
    @ApiOperation("微信支付")
    public String wxPay(String openId, String orderNumber ,int price ,HttpServletRequest request) {
        Object result = new Object();
        try {
            //获取客户端的ip地址
            String spbill_create_ip = getIpAddr(request);
            //将支付业务下沉到service层
            result = weixinService.wxPay(spbill_create_ip, openId, orderNumber, price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PayUtil.toJson(result);
    }


    @RequestMapping(value = "/api_p/pay/refund", method = RequestMethod.POST)
    @ApiOperation("微信退款-订单退款")
    public String refund(String openId, String orderNumber, int price, HttpServletRequest request) {
        Object result = new Object();
        try {
            //获取客户端的ip地址
            String spbill_create_ip = getIpAddr(request);
            //将支付业务下沉到service层
            result = weixinService.wxRefund(spbill_create_ip, openId, orderNumber, price);
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
            if (PayUtil.verify(prestr, (String) map.get("sign"), WechatConfig.secret, "utf-8")) {

                /**此处添加自己的业务逻辑代码start**/
                String orderNo = (String) map.get("out_trade_no");// 商户订单号
                String amountpaid = (String)map.get("total_fee");// 实际支付的订单金额:单位 分
                BigDecimal amountPay = (new BigDecimal(amountpaid).divide(new BigDecimal("100"))).setScale(2);// 将分转换成元-实际支付金额:元

                weixinService.updatePaySuccess(orderNo, amountPay, "微信支付");
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

    //退款回调接口哦
    @RequestMapping(value = "/api_p/pay/wxRefundNotify", method = RequestMethod.POST)
    public void refundCallback(HttpServletRequest request, HttpServletResponse response) {
        logger.info("退款  微信回调接口方法 start");
        String inputLine = "";
        String notityXml = "";
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            //关闭流
            request.getReader().close();
            logger.info("退款  微信回调内容信息：" + notityXml);
            //解析成Map
            Map<String, String> map = PayUtil.doXMLParse(notityXml);
            //判断 退款是否成功
            if ("SUCCESS".equals(map.get("return_code"))) {
                logger.info("退款  微信回调返回是否退款成功：是");
                //获得 返回的商户订单号
                ///  String passMap = AESUtil.decryptData(map.get("req_info"));
                //拿到解密信息
                //   map = PayUtil.doXMLParse(passMap);
                //拿到解密后的订单号
                String outTradeNo = map.get("out_trade_no");

                logger.info("退款  微信回调返回商户订单号：" + map.get("out_trade_no"));
                //支付成功 修改订单状态 通知微信成功回调
             /*   int sqlRow = orderJpaDao.updateOrderStatus("refunded",new Timestamp(System.currentTimeMillis()), outTradeNo);
                if(sqlRow == 1) {
                    logger.info("退款 微信回调 更改订单状态成功");
                }*/
            } else {
                //获得 返回的商户订单号
                ///  String passMap = AESUtil.decryptData(map.get("req_info"));
                //拿到解密信息
                //    map = PayUtil.doXMLParse(passMap);
                //拿到解密后的订单号
                String outTradeNo = map.get("out_trade_no");
                //更改 状态为取消
             /*   int sqlRow = orderJpaDao.updateOrderStatus("canceled",new Timestamp(System.currentTimeMillis()), outTradeNo);
                if(sqlRow == 1) {
                    logger.info("退款 微信回调返回是否退款成功：否");
                }*/
            }
            response.setContentType("text/xml");
            //给微信服务器返回 成功标示 否则会一直询问 咱们服务器 是否回调成功
            PrintWriter writer = response.getWriter();
            //封装 返回值
            StringBuffer buffer = new StringBuffer();
            buffer.append("<xml>");
            buffer.append("<return_code>SUCCESS</return_code>");
            buffer.append("<return_msg>OK</return_msg>");
            buffer.append("</xml>");
            //返回
            writer.print(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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


   /* @RequestMapping(value = "/api_p/pay/surplusPay", method = RequestMethod.POST)
    @ApiOperation("余额支付")
    public HashMap surplusPay(String orderNo, BigDecimal amount) {
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_FAILED);
        try {
            //
            OrderModel oModel = orderService.getByOrderNo(orderNo);
            if (null == oModel) {
                returnMap.put("message", "订单不存在");
                return returnMap;
            }
            String userId = oModel.getUserId();
            UserModel userModel = userService.selectById(userId);
            BigDecimal userSurplusMoney = userModel.getSurplusMoney();
            BigDecimal surplusMoney = userSurplusMoney.subtract(amount);
            if (userSurplusMoney.compareTo(amount) < 0) {
                returnMap.put("message", "余额不足，请充值！");
                return returnMap;
            }
            userService.updateUserMoney(userId, amount.negate());
            //修改订单表状态
            orderService.updatePaySuccess(orderNo, 1, "2");

            //消费记录表 增加一条收支记录
            ConsumeRecordsModel model = new ConsumeRecordsModel();
            model.init(model);
            model.setUserId(userId);
            model.setConsumeType(oModel.getOrderType());
            model.setConsumMoney(amount);
            model.setSurplusMoney(surplusMoney);
            ConsumeRecordsService.insert(model);
            returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return returnMap;
    }


    @RequestMapping(value = "/api_p/pay/recharge", method = RequestMethod.POST)
    @ApiOperation("微信支付-金额充值")
    public String recharge(String openId, int amount, HttpServletRequest request) {
        Object result = new Object();
        try {
            //生成订单
            OrderModel orderModel = new OrderModel();
            orderModel.init(orderModel);
            String orderNo = OrderUtils.getOrderCode(openId);
            orderModel.setOrderNo(orderNo);
            orderModel.setUserId(openId);
            orderModel.setO_status("1");//1有效
            orderModel.setOrderStatus("0");
            orderModel.setIsPay("0");
            orderModel.setAmount(amount / 100 + "");
            orderModel.setOrderType("2");//2为充值
            orderModel.setExpireTime(DateUtils.getSomeTime(new Date(), 5));
            Integer x = orderService.insert(orderModel);

            //微信支付
            if (x == 1) {
                //获取客户端的ip地址
                String spbill_create_ip = getIpAddr(request);
                //将支付业务下沉到service层
                result = weixinService.wxPay(spbill_create_ip, openId, orderNo, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "系统错误";
        }
        return PayUtil.toJson(result);
    }
*/

    @RequestMapping(value = "/api_p/pay/teset", method = RequestMethod.POST)
    @ApiOperation("微信支付-回调测试")
    public String teset(String orderNo, BigDecimal amountPay, HttpServletRequest request) {
        Object result = new Object();
        try {
            weixinService.updatePaySuccess(orderNo, amountPay, "微信支付");

        } catch (Exception e) {
            e.printStackTrace();
            result = "系统错误";
        }
        return PayUtil.toJson(result);
    }



    @RequestMapping(value = "/api_p/pay/businessPay", method = RequestMethod.POST)
    @ApiOperation("商户支付-商户支付")
    public HashMap wxPayByEnterprise(HttpServletRequest request, String openid, Double money) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("status", "0");
        if (money == null || StringUtils.isBlank(openid)) {
            hashMap.put("message", "支付金额为空或openid为空");
            return hashMap;
        }

        if (money.doubleValue() < 1.0D) {
            hashMap.put("message", "提现金额最低为1元");
            return hashMap;
        }
        if (money.doubleValue() < 1000.0D) {
            hashMap.put("message", "提现金额最低为1000元");
            return hashMap;
        }
      //查询用户信息

       /* if (userModel.isEmpty()) {
            hashMap.put("message", "该账户信息不存在！");
            return hashMap;
        }
*/

  /*      if (((WeiXinUserPOJO) list.get(0)).getMoney().doubleValue() < money.doubleValue()) {
            hashMap.put("message", "您提现的金额超出账户余额！！！");
            return hashMap;
        }*/

        money = Double.valueOf(money.doubleValue() - 0.03D * money.doubleValue());


        String nonce_str = weixinService.generateNonceStr();
        String checkName = "NO_CHECK";
        String spbill_create_ip = getIpAddr(request);
        //  String partner_trade_no = PayUtils.getOrderIdByTime();
        //订单号
        String partner_trade_no = "";
        String total_money = String.valueOf(money);
        String desc = "提现" + money + "元";

        long b = Math.round(money.doubleValue() * 100.0D);

        Integer amount = Integer.valueOf(String.valueOf(b));
        Map<String, String> parameters = new HashMap<>();
        parameters.put("mch_appid", WechatConfig.appid);
        parameters.put("mchid", WechatConfig.mch_id);
        parameters.put("partner_trade_no", partner_trade_no);
        parameters.put("nonce_str", nonce_str);
        parameters.put("openid", openid);
        parameters.put("check_name", checkName);
        parameters.put("amount", amount+"");
        parameters.put("spbill_create_ip", spbill_create_ip);
        parameters.put("desc", desc);
       // String sign = WXSignUtils.createSign("UTF-8", parameters);
        String prestr = PayUtil.createLinkString(parameters); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        logger.info("----------生成签名字符串:" + prestr);
        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        String sign = PayUtil.sign(prestr, WechatConfig.secret, "utf-8").toUpperCase();
        parameters.put("sign", sign);
        logger.info("----------mysign:" + sign);
        //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
        String xml = PayUtil.map2XmlString(parameters);
        //调用统一下单接口，并接受返回的结果
        String result = PayUtil.httpRequest(WechatConfig.merchants_url, "POST", xml);

           /* this.transfers.setAmount(amount);
            this.transfers.setCheck_name(checkName);
            this.transfers.setDesc(desc);
            this.transfers.setMch_appid(appid);
            this.transfers.setMchid(mch_id);
            this.transfers.setNonce_str(nonce_str);
            this.transfers.setOpenid(openid);
            this.transfers.setPartner_trade_no(partner_trade_no);
            this.transfers.setSign(sign);
            this.transfers.setSpbill_create_ip(spbill_create_ip);*/
    /*        String xmlInfo = XmlUtils.javaBeanToXml(this.transfers);
            try {
                CloseableHttpResponse response = HttpUtil.Post(
                       WechatConfig.merchants_url, xmlInfo,
                        weiXinParmsPOJO.getCertificateUrl(), weiXinParmsPOJO.getMchId(), true);
                String transfersXml = EntityUtils.toString(response.getEntity(), "utf-8");
                Map transferMap = XmlUtils.getXmlBodyContext(transfersXml);
                if (transferMap.size() <= 0)
                    if ((transferMap.get("result_code").equals("SUCCESS"))
                            && (transferMap.get("return_code").equals("SUCCESS"))) {
                        ((WeiXinUserPOJO) list.get(0)).setMoney(Double.valueOf(
                                ((WeiXinUserPOJO) list.get(0)).getMoney().doubleValue() - money.doubleValue()));
                        int updateByPrimaryKeySelective = this.weiXinUserPOJODAO
                                .updateByPrimaryKeySelective((WeiXinUserPOJO) list.get(0));
                        if (updateByPrimaryKeySelective == 0)
                            hashMap.put("message", "提现成功");
                        hashMap.put("status", "1");
                        WeiXinLogPOJO weiXinLogPOJO = new WeiXinLogPOJO();
                        weiXinLogPOJO.setIp(PayUtils.getIpAddr(request));
                        weiXinLogPOJO.setMoney(total_money);
                        weiXinLogPOJO.setType("提现");
                        weiXinLogPOJO.setOpenid(openid);
                        weiXinLogPOJO.setOrderId(((WeiXinUserPOJO) list.get(0)).getId());
                        weiXinLogPOJO.setCreateTime(new Date());
                        weiXinLogPOJODAO.insert(weiXinLogPOJO);
                        return hashMap;
                    }*/

         /*       hashMap.put("message", transferMap.get("err_code_des"));
            } catch (Exception e) {
                e.printStackTrace();

                hashMap.put("message", "提现失败");
            }
        } else {
            hashMap.put("message", "提现失败！");
        }*/
        return hashMap;
    }









}
