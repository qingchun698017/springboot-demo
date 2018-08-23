/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.interceptor.model;

import lombok.Data;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-12 15:25
 */
@Data
public class MethodInfo {
    private String clazz;
    private String method;
    private Long restTime;
    private Object[] param;
    private Object resultBody;
    private String clientIp;
}
