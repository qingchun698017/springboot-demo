/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.interceptor;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-14 21:02
 */
public interface IMockData {
    /**
     * 方法名称
     */
    String METHOD_NAME = "mock";

    Object mock();
}
