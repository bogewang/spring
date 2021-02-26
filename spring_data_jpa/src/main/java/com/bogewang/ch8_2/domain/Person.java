package com.bogewang.ch8_2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and" +
        " address=?2")
public class Person {
    @Id
    @GeneratedValue     //创建序列自增
    private Long id;

    private Integer age;

    private String name;

    public Person() {
    }

    public Person(Long id,  String name,Integer age, String address) {
        super();
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

}
