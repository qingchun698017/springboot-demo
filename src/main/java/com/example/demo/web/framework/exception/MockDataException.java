/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.demo.web.framework.exception;

/**
 * TODO
 *
 * @author wqc
 * @version V1.0
 * @since 2018-07-17 22:22
 */
public class MockDataException extends RuntimeException {

    private Object mock;

    public MockDataException(Object mock) {
        this.mock = mock;
    }

    public Object getMock() {
        return mock;
    }
}
