package com.bogeweang.ch8_4.service;

import com.bogeweang.ch8_4.domain.Person;

public interface DemoService {

    Person savePersonWithRollBack(Person person);

    Person savePersonWithoutRollBack(Person person);
}
