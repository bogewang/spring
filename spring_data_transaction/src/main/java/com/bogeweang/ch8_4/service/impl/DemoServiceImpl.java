package com.bogeweang.ch8_4.service.impl;

import com.bogeweang.ch8_4.dao.PersonRepository;
import com.bogeweang.ch8_4.domain.Person;
import com.bogeweang.ch8_4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;  //1

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})//2 使用@Transactional 注解的rollbackFor属性, 制定特定异常时,数据回滚
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if ("汪云飞".equals(person.getName())){
            throw new IllegalArgumentException("汪云飞已经存在,数据将回滚");        //3
        }
        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})    //4 制定特定异常时,数据回滚;
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if ("汪云飞".equals(person.getName())){
            throw new IllegalArgumentException("汪云飞虽已存在,数据将不会回滚!");
        }
        return p;
    }
}
