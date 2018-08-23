package com.example.demo.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.web.dataobject.Person;
import com.example.demo.web.mapper.PersonMapper;
import com.example.demo.web.service.PersonService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wlei
 * @version 1.0
 * @Description:person-业务逻辑实现类
 * @date 2018/8/4 19:40:12
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonMapper personMapper;

    public Long add(Person person) {
        log.info("入参personDO={}", JSON.toJSONString(person, true));
        return personMapper.insert(person);
    }

    public int delete(Long id) {
        return personMapper.delete(id);
    }

    public int update(Person person) {
        return personMapper.update(person);
    }

    public Person get(Long id) {
        return personMapper.select(id);
    }

    @Override
    public List<Person> getList() {
        return personMapper.selectAll();
    }

}
