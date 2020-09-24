package com.gyb.base.shrio;

import com.gyb.base.model.SysLoginModel;
import com.gyb.base.model.SysPermissionModel;
import com.gyb.base.model.SysRoleModel;
import com.gyb.base.service.*;
import com.gyb.base.util.PinYinHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Description:
 * Date: 2019-01-03
 * Time: 13:27
 */
public class MyShiroRealm extends AuthorizingRealm {
    private Logger logger = LogManager.getLogger(getClass());

    @Resource
    private SysLoginService sysLoginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysLoginModel sysLoginModel = (SysLoginModel) principals.getPrimaryPrincipal();
        for (SysRoleModel role : sysLoginModel.getRoles()) {
            authorizationInfo.addRole(role.getRole_code());
            for (SysPermissionModel p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission_code());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        logger.info("用户登陆验证：" + userName);
        SysLoginModel sysLoginModel = sysLoginService.selectModelByUserName(userName);
        if (sysLoginModel == null) {
            throw new UnknownAccountException();
        } else if (sysLoginModel.getIs_locked() == 1) {
            throw new LockedAccountException();
        } else {
            sysLoginModel = sysLoginService.selectDetailsById(sysLoginModel.getId());
            if (token instanceof MyUsernamePasswordToken && ((MyUsernamePasswordToken) token).getType().equals(LoginType.NO_PASSWORD)) {
                //免密登陆
                return new SimpleAuthenticationInfo(sysLoginModel, "", getName());
            } else {
                String pingYin = PinYinHelper.getPingYin(sysLoginModel.getUser_name());
                Subject subject = SecurityUtils.getSubject();
                String password = sysLoginModel.getPassword();
                sysLoginModel.setPassword(null);
                subject.getSession().setAttribute("loginPOJO", sysLoginModel);
                return new SimpleAuthenticationInfo(
                        sysLoginModel,
                        password,
                        ByteSource.Util.bytes(pingYin),
                        getName()
                );
            }
        }
    }
}