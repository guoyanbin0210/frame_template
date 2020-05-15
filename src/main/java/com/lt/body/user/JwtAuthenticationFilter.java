package com.lt.body.user;

import com.lt.body.user.model.UserMainModel;
import com.lt.body.user.service.UserMainService;
import com.lt.body.user.utils.JwtUtil;
import org.apache.shiro.util.AntPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: JwtAuthenticationFilter
 * Description:
 * date: 2019/5/14 16:45
 *
 * @author GaoShan
 */

// 注入spring容器
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final UserMainService villageUserService;
    public JwtAuthenticationFilter(UserMainService villageUserService) {
        this.villageUserService = villageUserService;
    }
    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) {
        /*
         * 这里有个大坑  就是跨域带自定义请求头的时候会先请求 opttions
         * */
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            LOGGER.info("-----检查 {} ------", RequestMethod.OPTIONS.name());
            response.setStatus(HttpStatus.OK.value());
            return;
        }
        try {
            if (isProtectedUrl(request)) {
                String token = request.getHeader("Authentication");
                if (token == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "为匹配到校验字段");
                    return;
                }
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回

                String userName = JwtUtil.verifyToken(token);
                UserMainModel model = villageUserService.selectByPhone(userName);
                if (model == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户信息异常");
                    return;
                }

//                if (oldTokenSet.contains(token)) {
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token已经被替换");
//                    return;
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("一个 token 异常进来了" + HttpServletResponse.SC_UNAUTHORIZED + "," + e.getMessage());
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

    }

    private boolean isProtectedUrl(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/api/**", request.getServletPath());
    }

    private boolean isPublicUrl(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/api_p/**", request.getServletPath());//获取验证码
    }
}
