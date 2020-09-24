package com.gyb.base.util;

import com.gyb.base.fileManager.constant.BaseFileConstant;
import com.gyb.base.model.SysLoginModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtils {

    /**
     * 加密密码
     */
    public static void encryptionPassword(SysLoginModel sysLoginModel) {
        String pingYin = PinYinHelper.getPingYin(sysLoginModel.getUser_name());
        String newPassword = new SimpleHash("md5", sysLoginModel.getPassword(),
                ByteSource.Util.bytes(pingYin), 2).toHex();
        sysLoginModel.setPassword(newPassword);
    }

    public static String getMd5(String salt, String password) {
        return new SimpleHash("md5", password, ByteSource.Util.bytes(salt), 2).toHex();
    }

    /**
     * 获取加密密码
     */
    public static String getPassword(String phone,String pwd){
        String pingYin = PinYinHelper.getPingYin(phone);
        String newPassword = new SimpleHash("md5", pwd, ByteSource.Util.bytes(pingYin), 2).toHex();
        return newPassword;
    }

    @Test
    public void aaa(){
        String a = "15022048477";
        String b = "litao123";
        String c = getPassword(a,b);
        System.out.println(c);
    }
    /**
     * @return 获取当前登陆用户对象
     */
    public static SysLoginModel getCurrLoginModel() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            SysLoginModel principal = (SysLoginModel) subject.getPrincipal();
            if (principal != null) {
                Object o = deepClone(principal);
                if (o != null) {
                    return (SysLoginModel) o;
                }
            }
        }
        return null;
    }

    /**
     * 克隆对象
     *
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deepClone(Object object) {
        Object resultObj = null;
        // 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(bo);
            oos.writeObject(object);
            // 从流里读出来
            ois = new ObjectInputStream(new ByteArrayInputStream(bo.toByteArray()));
            resultObj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultObj;
    }


    /**
     * @return 获取32位大写UUID 不包含"-"
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * @param count 位数
     * @return 随机数字符串
     */
    public static String getRandom(int count) {
        StringBuilder str = new StringBuilder();
        for (int index = 0; index < count; index++) {
            int min = 1;
            int max = 9;
            Random random = new Random();
            int r = random.nextInt(max) % (max - min + 1) + min;
            str.append(r);
        }
        return str.toString();
    }

    public static String getSystemTypePath() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            //如果是Windows系统
            return BaseFileConstant.StaticPath.UPLOAD_LOCAL_PATH.getWinLocPath();
        } else {
            //linux 和mac
            return BaseFileConstant.StaticPath.UPLOAD_LOCAL_PATH.getLinLocPath();
        }
    }

    public static String getDownloadPath() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            //如果是Windows系统
            return BaseFileConstant.StaticPath.STATIC_APK.getWinLocPath();
        } else {
            //linux 和mac
            return BaseFileConstant.StaticPath.STATIC_APK.getLinLocPath();
        }
    }

    public static String getUeUploadPath() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            //如果是Windows系统
            return BaseFileConstant.StaticPath.STATIC_UE.getWinLocPath();
        } else {
            //linux 和mac
            return BaseFileConstant.StaticPath.STATIC_UE.getLinLocPath();
        }
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 验证手机号合法性
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
