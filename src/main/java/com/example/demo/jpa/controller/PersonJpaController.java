/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.jpa.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.jpa.PersonRepository;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-08-07 21:54
 */
@RestController
public class PersonJpaController {
    @Resource
    private PersonRepository personRepository;

    @RequestMapping("/findAllPersonJpa")
    public String getAllPerson() {
        return JSON.toJSONString(personRepository.findAll());
    }
}
