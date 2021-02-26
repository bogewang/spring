package com.bogeweang.ch8_6_1.dao;

import com.bogeweang.ch8_6_1.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, Long> {
    Person findByName(String name); //1 支持方法名查询

    @Query("{'age':?0}")    //2 支持@Query查询,查询参数构造JSON字符串即可;
    List<Person> withQueryFindByAge(Integer age);
}
