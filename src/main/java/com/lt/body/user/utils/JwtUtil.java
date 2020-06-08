package com.lt.body.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.testng.annotations.Test;

import java.util.*;

public class JwtUtil {
    private final static String SECRET = "ggg443256291";
    private final static String IS_SUER = "SERVICE";//签名是有谁生成 例如 服务器
    private final static String SUBJECT = "SUBJECT";//签名的主题
    private final static String AUDIENCE = "H5";//签名的观众 也可以理解谁接受签名的


    @Test
    public void test() {

        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJTVUJKRUNUIiwiYXVkIjoiSDUiLCJpc3MiOiJTRVJWSUNFIiwidXNlck5hbWUiOiIwREY5MkRBOUI5OEY0QTA3OEYwRjQ3NzJBNEYzOTY1MiIsImV4cCI6MTU2MjMwNDY2MywiaWF0IjoxNTYxNjk5ODYzfQ.rapYZxsTTgWPOWmRQaii5cKFUWJ_V7j3td6L02XoJuw
        //0DF92DA9B98F4A078F0F4772A4F39652
        String token = createTokenWithClaim("15501022222");
        System.out.println(token);

        String userName2 = JwtUtil.verifyToken(token);
        System.out.println(userName2);
    }


    public static String createTokenWithClaim(String userName, String secret) throws JWTCreationException {
        return getString(userName, secret);
    }


    public static String createTokenWithClaim(String userName) throws JWTCreationException {
        return getString(userName, SECRET);
    }


    private static String getString(String userName, String secret) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = getAfterDate(nowDate, 0, 1, 0, 0, 0, 0);//2小过期
        return JWT.create()
                /*设置头部信息 Header*/
                .withHeader(map)
                /*设置 载荷 Payload*/
                .withClaim("userName", userName)
                .withIssuer(IS_SUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .withIssuedAt(nowDate) //生成签名的时间
                .withExpiresAt(expireDate)//签名过期的时间
                .sign(algorithm);
    }

    /**
     * 验证并解析
     *
     * @param token
     * @return userName
     * @throws JWTVerificationException
     */
    public static String verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Map<String, Claim> claims = JWT.require(algorithm).withIssuer(IS_SUER).build().verify(token).getClaims();
        Claim claim = claims.get("userName");
        return claim.asString();
    }

    /**
     * 返回一定时间后的日期
     *
     * @param date   开始计时的时间
     * @param year   增加的年
     * @param month  增加的月
     * @param day    增加的日
     * @param hour   增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }


}
