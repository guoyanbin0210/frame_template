package com.gyb.base.aop;

import com.gyb.base.model.SysLoginModel;
import com.gyb.base.model.SysOperationLogModel;
import com.gyb.base.service.SysOperationLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Component
@Aspect
public class ControllerAspect {
    private static Logger logger = LogManager.getLogger(ControllerAspect.class.getName());

    @Resource
    private SysOperationLogService sysOperationLogService;

    @Pointcut("@annotation(ControllerMethodLog)")
    public void controllerMethodLog() {
    }


    @Before("execution(* com.gyb.body.business.controller.OrderController.test(..))")
    public void beforeTest(JoinPoint joinPoint) {
        Object[] obj = joinPoint.getArgs();
        System.out.println("joinPoint = " + joinPoint);
        System.out.println("他来了！");
    }


    @Before(value = "controllerMethodLog()")
    public void beforeController(JoinPoint joinPoint) {
      //  logger.info("----------- Request Start -----------");
        Object[] obj = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
      //  logger.info("{} {}", signature.getName(), signature.getDeclaringTypeName());
        if(signature.getName().equals("doLogin")){
        }else{
            signature.getDeclaringType();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
               // logger.info("{} {} {}",request.getRemoteAddr(),request.getMethod(), request.getRequestURL().toString());
//                logger.info("{}", JSON.toJSON(request.getParameterMap()));
            }
        }
       //logger.info("----------- Request End -------------");

    }


    @AfterReturning(value = "controllerMethodLog()", returning = "result")
    @Async
    public void afterReturnControllerMethodLog(JoinPoint joinPoint, Object result) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (targetClass != null) {
            Method[] methods = targetClass.getMethods();
            String description = null;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    if (method.getParameterTypes().length == arguments.length) {
                        description = method.getAnnotation(ControllerMethodLog.class).remark();
//                        logger.info(method.getName()+","+description);
//                        logger.info(result.toString());
                        break;
                    }
                }
            }
            if (description != null) {
                Subject subject = SecurityUtils.getSubject();
                if (subject != null) {
                    if(subject.getPrincipals()!=null){
                        SysLoginModel sysLoginModel = (SysLoginModel) subject.getPrincipals().getPrimaryPrincipal();
                        if (sysLoginModel != null) {
                            SysOperationLogModel sysOperationLogModel = new SysOperationLogModel();
                            sysOperationLogModel.init(sysOperationLogModel);
                            sysOperationLogModel.setSol_des(description);
                            sysOperationLogModel.setSol_login_id(sysLoginModel.getId());
                            sysOperationLogService.insert(sysOperationLogModel);
                        }
                    }
                }
            }
        }
    }
}
