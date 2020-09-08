package com.lt.config;

public class WechatConfig {
    //小程序appid
    public static final String appid = "wx885db970b0f638ae";
    //商户id
    public static final String mch_id = "1235991202";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //小程序密钥
    public static final String secret = "111";
    //商户密钥
    public static final String PAY_SECRET = "miyun360360360360360360360360360";


    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String notify_url = "tongcheng.miyunjingtai.com/api_p/pay/wxNotify";

    //消息推送模版id
    public static final String template_id="";

    //消息推送
    public static final String message_send = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信退款接口地址
    public static final String refund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //商户支付地址
    public static final String merchants_url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";


    //退款成功回调地址
    public static final String notify_refund_url = "shequtong.comwinwin.com/api_p/pay/wxRefundNotify";


//========================================H5========================================

    //授权登录获取用户信息
    public static final String grant_type="authorization_code";
    //获取access_token
    public static final String grant_type_access_token="client_credential";

}
