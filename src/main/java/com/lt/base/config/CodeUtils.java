package com.lt.base.config;

import com.aliyun.mns.common.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CodeUtils {
    //发送短信验证码模板
    //发送短信验证码模板
    public static String sendMsg(String phoneNumber,String signName,String msgMode){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "0");
        map.put("message", "获取验证码失败");
        //设置超时时间-可自行调整
        System.setProperty("sun.net .client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net .client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String product1 = "素质答题抽奖";
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI4Fjit3NB9WDBMCj3QMMB";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "YIufwODA11gdRquvMd5oWh1OjH5T8X";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // request.setSignName("金融科平台手机注册");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(msgMode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //System.out.println((int)((Math.random()*9+1)*10000)
        String checkCode = String.valueOf((int)((Math.random()*9+1)*100000));

        //String checkCode = getRandom(5);
        // request.setTemplateParam("{\"product\":\"您的验证码为:\", \"code\":\"" + checkCode + "\"}");
        request.setTemplateParam("{ \"code\":\"" + checkCode + "\",\"product\":\"" + product1 + "\"}");
        // request.setTemplateParam("{验证码\"code\":${code}，您正在登录${product}，若非本人操作，请勿泄露}");

        request.setOutId(phoneNumber);
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ServerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        String code = sendSmsResponse.getCode() ;
        System.out.println("**************************"+code);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            map.put("sendSmsResponse", sendSmsResponse);
            return checkCode;
        } else {
            map.put("sendSmsResponse", sendSmsResponse);
            map.put("status", 2);
            return "服务器内部错误";
        }
        //return null;
    }


    //审核通过模板
    public static String auditPass(String phoneNumber){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "0");
        map.put("message", "获取验证码失败");
        //设置超时时间-可自行调整
        System.setProperty("sun.net .client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net .client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIHsd1fjfiFPxm";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "IrpukqFVDqxBHbfl5lAAMcL9K5Tu6D";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("身份验证");

        request.setTemplateCode("SMS_180950239");

        request.setOutId(phoneNumber);
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ServerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {

            e.printStackTrace();
        }
        String code = sendSmsResponse.getCode() ;
        System.out.println("**************************"+code);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            map.put("sendSmsResponse", sendSmsResponse);
            return "审核通过";
        } else {
            map.put("sendSmsResponse", sendSmsResponse);
            map.put("status", 2);
            return "服务器内部错误";
        }
        //return null;
    }


    //审核不通过模板
    public static String auditNoPass(String phoneNumber,String str){
        HashMap<String, Object> map = new HashMap<>();
        //设置超时时间-可自行调整
        System.setProperty("sun.net .client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net .client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIHsd1fjfiFPxm";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "IrpukqFVDqxBHbfl5lAAMcL9K5Tu6D";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            try {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("身份验证");

        request.setTemplateCode("SMS_180950244");

        String reason = str;
        // request.setTemplateParam("{验证码\"code\":${code}，您正在登录${product}，若非本人操作，请勿泄露}");
        //request.setTemplateParam("{\"\"尊敬的用户，您的验证码为\", \"code\":\"请勿告诉他人，5分钟内有效！" + checkCode + "\"}");
        request.setTemplateParam("{ \"reason\":\"" + reason + "\"}");
        request.setOutId(phoneNumber);
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            try {
                sendSmsResponse = acsClient.getAcsResponse(request);
            } catch (ServerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (com.aliyuncs.exceptions.ClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClientException e) {

            e.printStackTrace();
        }
        String code = sendSmsResponse.getCode() ;
        System.out.println("**************************"+code);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            map.put("sendSmsResponse", sendSmsResponse);
            return reason;
        } else {
            map.put("sendSmsResponse", sendSmsResponse);
            map.put("status", 2);
            return "服务器内部错误";
        }
        //return null;
    }



}
