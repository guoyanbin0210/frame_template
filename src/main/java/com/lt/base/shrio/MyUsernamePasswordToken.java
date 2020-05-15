package com.lt.base.shrio;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created with GaoShan.
 * Description: 默认登陆方式为密码登陆
 * Date: 2019-01-03
 * Time: 13:15
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken {
    private LoginType type = LoginType.PASSWORD;

    public LoginType getType() {
        return type;
    }

    public void setType(LoginType type) {
        this.type = type;
    }

    /**
     * 免密登录
     */
    public MyUsernamePasswordToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NO_PASSWORD;
    }


    /**
     * 密码登陆
     * @param username 用户名
     * @param password 密码
     */
    public MyUsernamePasswordToken(String username, String password) {
        super(username, (password != null ? password.toCharArray() : null), false, null);
    }

}