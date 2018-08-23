package com.example.demo.web.service;

import com.example.demo.web.dataobject.Person;

import java.util.List;

/**
 * @author wlei
 * @version 1.0
 * @Description:person-业务逻辑接口类
 * @date 2018/8/4 19:40:12
 */
public interface PersonService {

    /**
     * 增加person
     *
     * @param person
     * @return
     */
    Long add(Person person);

    /**
     * 删除person
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新person
     *
     * @param person
     * @return
     */
    int update(Person person);

    /**
     * 查询person（某一个）
     *
     * @param id
     * @return
     */
    Person get(Long id);

    /**
     * 查询person（分页）
     *
     * @param pageIndex 当前页索引
     * @param pageSize  页大小
     * @return
     */
    List<Person> getList();
}

