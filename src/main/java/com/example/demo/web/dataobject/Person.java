package com.example.demo.web.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author wlei
 * @version 1.0
 * @Description:person-DO类
 * @date 2018/8/4 19:40:11
 */
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long age;

    private String name;

}
