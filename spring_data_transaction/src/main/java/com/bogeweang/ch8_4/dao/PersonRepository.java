package com.bogeweang.ch8_4.dao;

import com.bogeweang.ch8_4.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
