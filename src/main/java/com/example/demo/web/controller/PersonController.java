/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.service.PersonService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-04 20:19
 */
@RestController
public class PersonController {
    @Resource
    private PersonService personService;

    @Value("${our.teem}")
    private String ourTeem;

    @RequestMapping("/finnAllPerson")
    public String findAllPerson(String arg) {
        System.out.println(ourTeem);
        return JSON.toJSONString(personService.getList());
    }

    @RequestMapping("/teem")
    public String index() {
        return ourTeem;
    }
}
