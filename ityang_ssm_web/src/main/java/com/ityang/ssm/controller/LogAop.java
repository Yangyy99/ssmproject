package com.ityang.ssm.controller;

import com.ityang.ssm.domain.SysLog;
import com.ityang.ssm.service.SysLogService;
import com.ityang.ssm.utils.UuidUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: aop 日志切面
 */
@Component
@Aspect
public class LogAop {

    //httpservletrequest
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Class clazz;
    private Method method;
    private Date startTime;


    //访问方法 之前
    @Before("execution(* com.ityang.ssm.controller.*.*(..))")
    public void before(JoinPoint jp) throws NoSuchMethodException {

        // 获取访问的类
        clazz = jp.getTarget().getClass();
        // 获取访问时间
        startTime = new Date();
        // 获取访问的方法
        String methodName = jp.getSignature().getName(); // 获取方法名称
        // 获取方法参数
        Object[] args = jp.getArgs();
        if (args != null) {
            if (args.length == 0) {
                method = clazz.getMethod(methodName);
            } else {
                Class[] classes = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    classes[i] = args[i].getClass();

                }
                method = clazz.getMethod(methodName, classes);
            }


        }


    }

    // 访问方法之后
    @After("execution(* com.ityang.ssm.controller.*.*(..))")
    public void after(JoinPoint jp) throws Exception {
        // 获取路径url
        String url = "";
        if (clazz != null &&!clazz.getName().equals("SysLogController") && method != null && clazz != LogAop.class) {
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {

                String clazzMappingName = clazzAnnotation.value()[0];
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    url = clazzMappingName + methodAnnotation.value()[0];
                }
            }
            // 执行时间
            Date date = new Date();
            long excuteTime = date.getTime() - startTime.getTime();
            // ip
            String ip = request.getRemoteAddr();
            //获取用户
            // 通过springsecurity
            SecurityContext context = SecurityContextHolder.getContext();
            User user = (User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();
            // 封装
            SysLog sysLog=new SysLog();
            sysLog.setId(UuidUtils.getUuid());
            sysLog.setUrl(url);
            sysLog.setExcuteTime(excuteTime);
            sysLog.setVisitime(startTime);
            sysLog.setUsername(username);
            sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());

            //
            sysLogService.insertLog(sysLog);
        }


    }


}
