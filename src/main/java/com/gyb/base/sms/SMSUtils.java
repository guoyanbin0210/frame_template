package com.gyb.base.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.gyb.base.model.SysSmsLogModel;
import com.gyb.base.model.SysSmsModelModel;
import com.gyb.base.service.SysSmsLogService;
import com.gyb.base.service.SysSmsModelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Date: 2018-08-06
 * Time: 11:13
 */
public class SMSUtils {
    /**
     * 发送登录验证码
     *
     * @param phoneNum
     * @return
     */
    public static HashMap<String, Object> sendLoginCode(String phoneNum, String code, SysSmsLogService smsLogService) {
        HashMap<String, Object> map = getDoFailedMap();
        SysSmsModelModel smsModelModel = new SysSmsModelModel();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("product",SmsConstant.CONFIG_SIGN_NAME);
        String param = jsonObject.toJSONString();
        smsModelModel.setSsm_content("验证码[xxx]，您正在注册成为[xxx]用户，感谢您的支持！");
        CommonResponse commonResponse = getCommonResponse(phoneNum, SmsConstant.CONFIG_LOGIN_CODE, param);
        return getStringObjectHashMap(phoneNum, smsLogService, map, param, smsModelModel, commonResponse);
    }

    /**
     * 发送注册验证码
     *
     * @param phoneNum
     * @return
     */
    public static HashMap<String, Object> sendSignCode(String phoneNum, String code, SysSmsLogService smsLogService) {
        HashMap<String, Object> map = getDoFailedMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("product",SmsConstant.CONFIG_SIGN_NAME);

        String param =jsonObject.toJSONString();
        SysSmsModelModel smsModelModel = new SysSmsModelModel();
        smsModelModel.setSsm_content("验证码["+code+"]，您正在登录["+SmsConstant.CONFIG_SIGN_NAME+"]，若非本人操作，请勿泄露。");
        CommonResponse commonResponse = getCommonResponse(phoneNum, SmsConstant.CONFIG_SIGN_CODE, param);
        return getStringObjectHashMap(phoneNum, smsLogService, map, param, smsModelModel, commonResponse);
    }

    /**
     * 短信通知
     *
     * @param phoneNumber
     * @param templateCode
     * @param params
     * @param smsLogService
     * @param smsModelService
     * @return
     */
    public static HashMap<String, Object> sendInformAndLog(
            String phoneNumber, String templateCode, String params,
            SysSmsLogService smsLogService, SysSmsModelService smsModelService) {
        HashMap<String, Object> map = getDoFailedMap();
        SysSmsModelModel smsModelModel = smsModelService.selectModelByCode(templateCode);
        CommonResponse commonResponse;
        if (smsModelModel != null) {
            commonResponse = getCommonResponse(smsModelModel.getSsm_signature_name(), phoneNumber, smsModelModel.getSsm_code(), params);
        } else {
            map.put("message", "模版不存在");
            return map;
        }
        SysSmsLogModel smsLogModel = new SysSmsLogModel();
        smsLogModel.init(smsLogModel);
        smsLogModel.setSsl_type(2);
        smsLogModel.setSsl_receive(phoneNumber);
        if (commonResponse != null) {
            String code = getString(map, commonResponse, smsLogModel);
            doInformLog(params, smsLogService, smsModelModel, smsLogModel, code, SmsConstant.MODEL_TYPE.SMS_INFORM);
            map.put("code", code);
            map.put("data", commonResponse.getData());
        }
        return map;
    }

    private static String getString(HashMap<String, Object> map, CommonResponse commonResponse, SysSmsLogModel smsLogModel) {
        smsLogModel.setSsl_return_msg(commonResponse.getData());
        JSONObject jsonObject = JSON.parseObject(commonResponse.getData());
        String code = jsonObject.getString("Code");
        String message = jsonObject.getString("Message");
        if (code.toUpperCase().equals("OK")) {
            map.put("status", 1);
            map.put("message", "发送成功");
            smsLogModel.setSsl_is_success(1);
        } else {
            map.put("message", message);
            smsLogModel.setSsl_is_success(0);
            smsLogModel.setSsl_count(0);
        }
        return code;
    }


    /**
     * 发送日志
     *
     * @param params
     * @param smsLogService
     * @param smsModelModel
     * @param smsLogModel
     * @param code
     */
    private static void doInformLog(String params, SysSmsLogService smsLogService, SysSmsModelModel smsModelModel, SysSmsLogModel smsLogModel, String code, SmsConstant.MODEL_TYPE type) {
        new Thread(new FutureTask<>(() -> {
            if (code.toUpperCase().equals("OK")) {
                int length = 0;
                String ssm_content = getSendBody(smsModelModel.getSsm_content(), JSONObject.parseObject(params));
                smsLogModel.setSsl_send_body(ssm_content);
                length = ssm_content.length();
                smsLogModel.setSsl_count(length / 67 + (length % 67 > 0 ? 1 : 0));//短信字数含"签名+模版内容+变量内容”，短信70个字数含以内，按1条短信计费；超出70个字为长短信，按照67个字数记为1条短信费用。
                smsLogModel.setSsl_body_len(length);
                smsLogModel.setSsl_model_code(smsModelModel.getSsm_code());
                smsLogModel.setSsl_type(type.getCode());
                smsLogService.insert(smsLogModel);
            }
            return null;
        })).start();
    }

    private static HashMap<String, Object> getStringObjectHashMap(String phoneNum, SysSmsLogService smsLogService, HashMap<String, Object> map, String param, SysSmsModelModel smsModelModel, CommonResponse commonResponse) {
        if (commonResponse != null) {
            SysSmsLogModel smsLogModel = new SysSmsLogModel();
            smsLogModel.init(smsLogModel);
            smsLogModel.setSsl_type(2);
            smsLogModel.setSsl_receive(phoneNum);
            String resCode = getString(map, commonResponse, smsLogModel);
            smsModelModel.setSsm_code(resCode);
            doInformLog(param, smsLogService, smsModelModel, smsLogModel, resCode, SmsConstant.MODEL_TYPE.SMS_CHECK_CODE);
            map.put("code", resCode);
            map.put("data", commonResponse.getData());
        }
        return map;
    }

    private static CommonResponse getCommonResponse(String signature_name, String phoneNumber, String templateCode, String params) {
    /*    DefaultProfile profile = DefaultProfile.getProfile("default", SmsConstant.CONFIG_ACCESS_KEY_ID, SmsConstant.CONFIG_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        if (StringUtils.isEmpty(signature_name)) {
            request.putQueryParameter("SignName", SmsConstant.CONFIG_SIGN_NAME);
        } else {
            request.putQueryParameter("SignName", signature_name);
        }
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", params);
        CommonResponse commonResponse = null;

        try {
            commonResponse = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return commonResponse;*/
    return null;
    }

    private static CommonResponse getCommonResponse(String phoneNumber, String templateCode, String params) {
        return getCommonResponse(null, phoneNumber, templateCode, params);
    }


    /**
     * "(\$\{[^}]*})"
     *
     * @param sendBody
     * @param params
     * @return
     */
    private static String getSendBody(String sendBody, JSONObject params) {
        for (Map.Entry<String, Object> entry : params.getInnerMap().entrySet()) {
            sendBody = sendBody.replace("${" + entry.getKey() + "}", (String) entry.getValue());
        }
        return sendBody;
    }


    /**
     * 使用正则表达式提取中括号中的内容
     *
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular(String msg) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group().substring(1, m.group().length() - 1));
        }
        return list;
    }

    private static HashMap<String, Object> getDoFailedMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "0");
        map.put("message", "短信通知发送失败");
        return map;
    }

}
