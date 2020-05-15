package com.lt.base.aop;

import com.lt.base.constant.BaseConstant;
import com.lt.base.util.BaseUtils;
import com.lt.base.util.RequestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with GaoShan.
 * Description:
 * Date: 2018-12-03
 * Time: 16:17
 */
@Component
@Aspect
public class AccessMethodIntervalAspect {
    private static Logger logger = LogManager.getLogger(ControllerAspect.class.getName());
    private static CopyOnWriteArrayList<HashMap<String, Object>> sWebSocketServers = new CopyOnWriteArrayList<>();

    @Pointcut("@annotation(com.lt.base.aop.AccessMethodInterval)")
    public void AccessMethodInterval() {
    }

    @Around(value = "AccessMethodInterval()")
    public Object beforeController(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("----------- Request Start -----------");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();
            String ipAddress = BaseUtils.getIPAddress(request);
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = null;
            try {
                targetClass = Class.forName(targetName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
            if (targetClass != null) {
                Method[] methods = targetClass.getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        if (method.getParameterTypes().length == arguments.length) {
                            logger.info(method.getName()+","+method.getAnnotation(AccessMethodInterval.class).count());
                            if (RequestUtils.doCheck(sWebSocketServers, request,  targetName+"-"+methodName,method.getAnnotation(AccessMethodInterval.class).time())) {
                                logger.info(method.getName()+","+"访问间隔请>"+method.getAnnotation(AccessMethodInterval.class).time());
                                throw new  Exception(BaseConstant.Response_MENU.REQUEST_REQUEST_CHECK.getDir()+","+"访问间隔请大于："+method.getAnnotation(AccessMethodInterval.class).time()+"ms");

//                                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//                                stringObjectHashMap.put("message", BaseConstant.Response_MENU.REQUEST_REQUEST_CHECK.getDir()+","+"访问间隔请大于："+method.getAnnotation(AccessMethodInterval.class).time()+"ms");
//                                stringObjectHashMap.put("status",BaseConstant.Response_MENU.REQUEST_REQUEST_CHECK.getCode());
//                                return  stringObjectHashMap;
                            }
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            response.sendError(BaseConstant.Response_MENU.REQUEST_REQUEST_CHECK.getCode(), e.getMessage());
            return null;
        }
        }
        logger.info("----------- Request End -------------");
        return joinPoint.proceed();
    }
}