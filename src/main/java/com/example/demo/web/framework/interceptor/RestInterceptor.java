/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.framework.exception.MockDataException;
import com.example.demo.web.framework.interceptor.model.RestInfo;
import com.example.demo.web.framework.mock.annotation.Mock;

import java.lang.reflect.Method;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-07-17 22:17
 */
@Component
public class RestInterceptor implements HandlerInterceptor {
    private static final Logger REST_LOG = LoggerFactory.getLogger("rest");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        //mock数据
        mock(request.getRequestURI(), handler);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        RestInfo restInfo = new RestInfo();
        restInfo.setIp(getClientIp(request));
        restInfo.setLocalIp(InetAddress.getLocalHost().getHostAddress());
        restInfo.setParam(JSON.toJSONString(request.getParameterMap()));
        restInfo.setHttpMethod(request.getMethod());
        restInfo.setUrl(request.getRequestURI());
        // restInfo.setClassMethod(handler.);
        //restInfo.setResultBody(response.);
        REST_LOG.info("rest : " + JSON.toJSONString(restInfo));
        System.out.println(("rest : " + JSON.toJSONString(restInfo)));

    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        REST_LOG.debug("x-forwarded-for,ip = {}", ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            REST_LOG.debug("Proxy-Client-IP,ip = {}", ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            REST_LOG.debug("WL-Proxy-Client-IP,ip = {}", ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            REST_LOG.debug("getRemoteAddr,ip = {}", ip);
        }
        if (StringUtils.isEmpty(ip) && ip.indexOf(',') > 0) {
            String[] arr = ip.split(",");
            ip = arr[0];
        }
        if (checkLocalhost(ip)) {
            String temp = request.getHeader("x-real-ip");
            REST_LOG.debug("x-real-ip,ip = {}", temp);
            if (StringUtils.isEmpty(temp)) {
                if (temp.indexOf(',') > 0) {
                    String[] arr = ip.split(",");
                    temp = arr[0];
                }
                ip = temp;
            }
        }
        REST_LOG.debug("ip = {}", ip);
        return ip;
    }

    /*
     * 判断是否局域网ip地址
     */
    public static boolean checkLocalhost(String ip) {
        if (StringUtils.isEmpty(ip))
            return true;

        return ip.startsWith("10.") || ip.startsWith("192.168.") || ip.equals("127.0.0.1");
    }

    /**
     * mock数据
     *
     * @param url
     * @param handler
     * @throws Exception
     */
    private void mock(String url, Object handler) throws Exception {
        try {
            //            if (!coreConfigProperties.mockEnable) {
            //                return;
            //            }
            Mock mock = ((HandlerMethod) handler).getMethod().getAnnotation(Mock.class);
            if (mock != null && mock.enable() && mock.mockClz() != null) {
                Class mockClz = mock.mockClz();
                Method method = mockClz.getMethod(IMockData.METHOD_NAME);
                throw new MockDataException(method.invoke(mockClz.newInstance()));
            }
        } catch (Exception e) {
            if (e instanceof MockDataException) {
                throw e;
            }
            REST_LOG.error("mock失败，url={}", url, e);
        }
    }

}
