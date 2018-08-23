/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.framework.interceptor.model.MethodInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteConnectFailureException;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-11 23:35
 */
public class ServiceInterceptor implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger("dependent");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Date startDate = new Date();
        int successState = 0;
        Object resultObject = null;
        try {
            resultObject = invocation.proceed();
        } catch (RemoteConnectFailureException e) {
            successState = 1;
            LOGGER.warn("{}.{} 接口请求超时", invocation.getMethod().getDeclaringClass().getName(),
                invocation.getMethod().getName(), e);
        } catch (Exception e) {
            successState = 1;
            LOGGER.warn("{}.{} 接口异常", invocation.getMethod().getDeclaringClass().getName(),
                invocation.getMethod().getName(), e);
        } catch (Throwable throwable) {
            successState = 1;
            LOGGER.warn("{}.{} 接口异常", invocation.getMethod().getDeclaringClass().getName(),
                invocation.getMethod().getName(), throwable);
        } finally {
            writeLog(invocation, startDate, resultObject, successState);
        }
        return resultObject;
    }

    private void writeLog(MethodInvocation invocation, Date startDate, Object resultObject, int successState) {
        Method method = invocation.getMethod();
        Class clz = method.getDeclaringClass();
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setClazz(clz.getName());
        methodInfo.setMethod(method.getName());
        methodInfo.setClientIp("");
        methodInfo.setParam(bytesParamFilter(invocation));
        Date endDate = new Date();
        methodInfo.setRestTime(endDate.getTime() - startDate.getTime());
        methodInfo.setResultBody(resultObject);
        LOGGER.info(JSON.toJSONString(methodInfo));
    }

    //    private String getCientIp() {
    //        // 暂时只实现获取dubbo的客户端地址
    //        return Optional.ofNullable(RpcContext.getContext()).
    //            filter(r -> r.getRemoteHost() != null).
    //            map(r -> r.getRemoteHost() + HOST_PORT_JOIN + r.getRemotePort()).orElse("");
    //    }

    private Object[] bytesParamFilter(MethodInvocation invocation) {
        Object[] arguments = invocation.getArguments();
        if (arguments == null || arguments.length == 0) {
            return new Object[0];
        }

        List<Object> objs = new ArrayList<>(arguments.length);
        for (Object obj : arguments) {
            if (obj instanceof byte[]) {
                objs.add("bytes");
            } else {
                objs.add(obj);
            }
        }
        return objs.toArray();
    }

}
