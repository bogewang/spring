package com.bogewang.ch8_2.web;

import com.bogewang.ch8_2.support.PersonRepository;
import com.bogewang.ch8_2.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    //1 spring data jpa 已经自动注册bean, 所以可以自动注入;
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存 支出批量保存<S extens T> Iterable <S> save (Iterable<S> entites);
     *
     * 删除 支持使用id删除对象,批量删除一级全部删除;
     * void delete (ID id);
     * void delete(T entity);
     * void delete(Itertable<? extends T> entites);
     * void deleteAll();
     *
     * @param name
     * @param address
     * @param age
     * @return
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age){
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    /**
     * 测试findByAddress
     * @param address
     * @return
     */
    @RequestMapping("/q1")
    public List<Person> findByAddress(String address){
        List<Person> list = personRepository.findByAddress(address);
        return list;
    }

    /**
     *
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q2")
    public Person findByNameAndAddress(String name, String address){
        Person p = personRepository.findByNameAndAddress(name, address);
        return p;
    }

    /**
     *
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q3")
    public Person withNameAndAddressQuery(String name, String address){
        Person person = personRepository.withNameAndAddressQuery(name, address);
        return person;
    }

    /**
     * 根据name和address获取person;
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q4")
    public Person withNameAndAddressNamedQuery(String name, String address){
        Person p = personRepository.withNameAndAddressNamedQuery(name, address);
        return p;
    }

    /**
     * 对结果进行排序
     * @return
     */
    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> list = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return list;
    }

    /**
     * 对结果进行分页;
     * @return
     */
    @RequestMapping("/page")
    public Page<Person> page(){
        Page<Person> all = personRepository.findAll(new PageRequest(1, 2));
        return all;
    }

    /**
     *
     * @param person
     * @return
     */
    @RequestMapping("/auto")
    public Page<Person> auto(Person person){
        Page<Person> pagePeople = personRepository.findByAuto(person, new PageRequest(0, 10));
        return pagePeople;
    }

}
