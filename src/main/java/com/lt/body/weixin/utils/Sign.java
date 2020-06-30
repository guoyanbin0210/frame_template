package com.lt.body.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sign {

	  public static Map<String, String> sign(String jsapi_ticket, String url) {
	        Map<String, String> ret = new HashMap<String, String>();
	        String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String string1;
	        String signature = "";
	        // 注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
	        System.out.println("string1:"+string1);
	 
	        try {
	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	            crypt.reset();
	            crypt.update(string1.getBytes("UTF-8"));
	            signature = byteToHex(crypt.digest());
	           // signature=	WeiXinUtils.getSignatrue(jsapi_ticket, nonce_str, timestamp, url);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	 
	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonce_str);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);
	 
	        return ret;
	    }
	 
	    // 生成签名
	    private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash) {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
	    }
	 
	    // 生成nonce_str
	    private static String create_nonce_str() {
	        return UUID.randomUUID().toString().replace("-", "");
	       // String uuid = UUID.randomUUID().toString().replace("-", "");  
	    }
	 
	    // 生成timestamp
	    private static String create_timestamp() {
	        return Long.toString(System.currentTimeMillis() / 1000);
	    }
}
