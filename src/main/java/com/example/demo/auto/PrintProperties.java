/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.auto;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-19 22:41
 */
@ConfigurationProperties(prefix = "init.print")
public class PrintProperties {
    private String message = "---------------------int print  -----------------------------";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
