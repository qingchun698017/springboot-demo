/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-19 00:18
 */
@Configuration
@EnableConfigurationProperties(PrintProperties.class)
@ConditionalOnProperty(prefix = "init.print.message", value = "enable")
public class PrintAutoConfiguration {
    @Autowired
    private PrintProperties printProperties;

    @Bean
    @ConditionalOnMissingBean(PrintAfterInitBean.class)
    public PrintAfterInitBean printAfterInitBean() {
        PrintAfterInitBean bean = new PrintAfterInitBean();
        bean.setMessage(printProperties.getMessage());
        return bean;
    }
}
