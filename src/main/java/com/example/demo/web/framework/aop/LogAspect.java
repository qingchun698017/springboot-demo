/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.framework.interceptor.model.MethodInfo;
import com.example.demo.web.framework.interceptor.model.RestInfo;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-12 22:03
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger REST_LOG = LoggerFactory.getLogger("rest");

    @Pointcut("execution(* com.example.demo.web.controller.*.*(..))")
    public void methodLog() {
    }

    ;

    @Around("methodLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        Date startDate = new Date();
        System.out.println("arround start----------------");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object resultObject = null;
        try {
            resultObject = pjp.proceed();
        } catch (RemoteConnectFailureException e) {
            REST_LOG.warn("{}.{} 接口请求超时", e);
        } catch (Exception e) {
            REST_LOG.warn("{}.{} 接口异常", e);
        } catch (Throwable throwable) {
            REST_LOG.warn("{}.{} 接口异常", throwable);
        } finally {
            // 记录下请求内容
            RestInfo restInfo = new RestInfo();
            restInfo.setClassMethod(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            restInfo.setHttpMethod(request.getMethod());
            restInfo.setIp(request.getRemoteAddr());
            restInfo.setParamArr(pjp.getArgs());
            restInfo.setResultBody(resultObject);
            REST_LOG.info("LogAspect:" + JSON.toJSONString(restInfo));
            System.out.println("LogAspect:" + JSON.toJSONString(restInfo));

            Signature signature = (Signature) pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();

            Class clz = method.getDeclaringClass();
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setClazz(clz.getName());
            methodInfo.setMethod(method.getName());
            methodInfo.setClientIp("");
            methodInfo.setParam(pjp.getArgs());
            Date endDate = new Date();
            methodInfo.setRestTime(endDate.getTime() - startDate.getTime());
            methodInfo.setResultBody(resultObject);
            REST_LOG.info("LogAspect methodInfo:" + JSON.toJSONString(methodInfo));

            System.out.println("LogAspect methodInfo:" + JSON.toJSONString(methodInfo));
        }
        return resultObject;
    }

}
