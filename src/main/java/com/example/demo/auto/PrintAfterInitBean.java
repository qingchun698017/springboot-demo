/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.auto;

import org.springframework.beans.factory.InitializingBean;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-19 00:11
 */
public class PrintAfterInitBean implements InitializingBean {
    private String message;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
