package com.bogewnag.ch8_5.service;

import com.bogewnag.ch8_5.domain.Person;

import java.util.Optional;

public interface DemoService {

    Person save(Person person);

    void remove(Long id);

    Optional<Person> findOne(Person person);
}
