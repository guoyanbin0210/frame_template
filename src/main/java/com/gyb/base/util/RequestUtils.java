package com.gyb.base.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


public class RequestUtils {

    /**
     * 校验相同ip 访问时间是否大于xxx秒
     * @param sWebSocketServers 
     * @param request
     * @param returnMap
     * @param srcTag
     * @return
     */
    public static boolean doCheck(
            CopyOnWriteArrayList<HashMap<String, Object>> sWebSocketServers,
            HttpServletRequest request,
            HashMap<String, Object> returnMap,
            String srcTag
    ) {
        String ip = BaseUtils.getIPAddress(request);
        boolean tagCheck = true;
        for (HashMap<String, Object> sWebSocketServer : sWebSocketServers) {
            Object tag = sWebSocketServer.get("tag");
            Object lastIp = sWebSocketServer.get("ip");
            if (Objects.equals(tag, srcTag) && Objects.equals(ip, lastIp)) {//
                long lastTime = (long) sWebSocketServer.get("lastTime");
                long currTime = System.currentTimeMillis();
                if ((currTime - lastTime) < 10 * 1000) {
                    returnMap.put("message", "访问时间请间隔10秒");
                    return true;
                } else {
                    sWebSocketServers.remove(sWebSocketServer);
                    sWebSocketServer.put("tag", srcTag);
                    sWebSocketServer.put("ip", ip);
                    sWebSocketServer.put("lastTime", System.currentTimeMillis());
               
                }
                tagCheck = false;
                break;
            }
        }
        if (tagCheck) {
            HashMap<String, Object> checkMap = new HashMap<>();
            checkMap.put("tag", srcTag);
            checkMap.put("ip", ip);
            checkMap.put("lastTime", System.currentTimeMillis());
            sWebSocketServers.add(checkMap);
        }
        return false;
    }
    public static boolean doCheck(
            CopyOnWriteArrayList<HashMap<String, Object>> sWebSocketServers,
            HttpServletRequest request,
            String srcTag,
            Long time
    ) {
        String ip = BaseUtils.getIPAddress(request);
        boolean tagCheck = true;
        for (HashMap<String, Object> sWebSocketServer : sWebSocketServers) {
            Object tag = sWebSocketServer.get("tag");
            Object lastIp = sWebSocketServer.get("ip");
            if (Objects.equals(tag, srcTag) && Objects.equals(ip, lastIp)) {//
                long lastTime = (long) sWebSocketServer.get("lastTime");
                long currTime = System.currentTimeMillis();
                if ((currTime - lastTime) < time) {
                    return true;
                } else {
                    sWebSocketServers.remove(sWebSocketServer);
                    sWebSocketServer.put("tag", srcTag);
                    sWebSocketServer.put("ip", ip);
                    sWebSocketServer.put("lastTime", System.currentTimeMillis());

                }
                tagCheck = false;
                break;
            }
        }
        if (tagCheck) {
            HashMap<String, Object> checkMap = new HashMap<>();
            checkMap.put("tag", srcTag);
            checkMap.put("ip", ip);
            checkMap.put("lastTime", System.currentTimeMillis());
            sWebSocketServers.add(checkMap);
        }
        return false;
    }

}
