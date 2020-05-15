package com.lt.base.sms;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-11-27
 * Time: 13:41
 */
public class SmsConstant {

    //阿里云帐号 hi30012302@aliyun.com
    public final static String CONFIG_ACCESS_KEY_ID = "LTAIHsd1fjfiFPxm";//你的accessKeyId,参考本文档步骤2
    public final static String CONFIG_ACCESS_KEY_SECRET = "IrpukqFVDqxBHbfl5lAAMcL9K5Tu6D";//你的accessKeySecret,参考本文档步骤2
    public final static String CONFIG_SIGN_NAME = "宜居密云";
    /**
     * 用户注册验证码
     * 验证码${code}，您正在注册成为${product}用户，感谢您的支持！
     */
    public final static String CONFIG_SIGN_CODE = "SMS_68265060";
    /**
     * 登录确认验证码
     * 验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。
     */
    public final static String CONFIG_LOGIN_CODE = "SMS_68265062";

    /**
     * 增:0,删:1,改:2.4.my0.1.5,查:3(默认为查)
     */
    public enum MODEL_TYPE {
        SMS_CHECK_CODE(0, "验证码"),
        SMS_INFORM(1, "短信通知"),
        ;
        private int code;
        private String dir;

        MODEL_TYPE(int code, String dir) {
            this.code = code;
            this.dir = dir;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
