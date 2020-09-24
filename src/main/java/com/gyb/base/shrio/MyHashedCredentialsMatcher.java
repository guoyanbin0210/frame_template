package com.gyb.base.shrio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        logger.debug("doCredentialsMatch");
        if (token instanceof MyUsernamePasswordToken) {
            if (((MyUsernamePasswordToken) token).getType().equals(LoginType.NO_PASSWORD)) {
                return true;
            }
        }
        return super.doCredentialsMatch(token, info);
    }
}