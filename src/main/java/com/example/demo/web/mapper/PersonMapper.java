package com.example.demo.web.mapper;

import com.example.demo.web.dataobject.Person;

import java.util.List;

/**
 * @author wqc
 * @version 1.0
 * @Description:person-数据访问接口类
 * @date 2018/8/4 19:40:12
 */
public interface PersonMapper {

    /**
     * 增加person
     *
     * @param person
     * @return
     */
    Long insert(Person person);

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
    Person select(Long id);

    /**
     * 查询person（全部）
     *
     * @return
     */
    List<Person> selectAll();
}