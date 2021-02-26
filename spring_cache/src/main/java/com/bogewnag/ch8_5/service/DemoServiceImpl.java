package com.bogewnag.ch8_5.service;

import com.bogewnag.ch8_5.dao.PersonRepository;
import com.bogewnag.ch8_5.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id")//1 people 缓存名称, key为person.id
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id,key为:" + p.getId() +"数据做了缓存!");
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {   //删除people中key为id的缓存;
        System.out.println("为id,key为:" + id +"数据删除了缓存");
        personRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "people", key = "#person.id")
    public Optional<Person> findOne(Person person) {
        Optional<Person> p = personRepository.findById(person.getId());
        System.out.println("为id,key为:" + person.getId() +"数据做了缓存!");
        return p;
    }
}
