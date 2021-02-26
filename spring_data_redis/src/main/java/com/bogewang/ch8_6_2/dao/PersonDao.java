package com.bogewang.ch8_6_2.dao;

import com.bogewang.ch8_6_2.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;    //1 springboot 已为我们配置Stringredistemplate,在此处可以直接注入

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr; //3 可以使用@Resource注解指定stringRedisTemplate,可以注入基于字符串的简单属性操作;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;    //2

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps; //4

    public void stringRedisTemplateDemo(){  //5
        valOpsStr.set("xx", "yy");
    }   //通过set方法,存储字符串类型;

    public void save(Person person){
        valOps.set(person.getId(), person); //6 存储对象类型
    }

    public String getString(){
        return valOpsStr.get("xx"); //7 获得字符串
    }
    public Person getPerson(){
        return (Person)valOps.get("1"); //8 获取对象
    }
}
