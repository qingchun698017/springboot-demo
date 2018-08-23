/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-02 22:46
 */
@RestController//类添加  @RestController默认类中的方法都会以json的格式返回
public class HelloWorldController {
    @RequestMapping("/index")
    public String index() {
        return "hello world~";
    }

}
