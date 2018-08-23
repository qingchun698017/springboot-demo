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
 * @since 2018-08-12 22:18
 */
@Data
public class RestInfo {
    private String url;
    private String httpMethod;
    private String ip;
    private String localIp;
    private String classMethod;
    private String param;
    private Object[] paramArr;
    private Object resultBody;

}
