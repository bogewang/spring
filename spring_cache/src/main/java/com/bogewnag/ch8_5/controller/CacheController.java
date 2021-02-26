package com.bogewnag.ch8_5.controller;

import com.bogewnag.ch8_5.domain.Person;
import com.bogewnag.ch8_5.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CacheController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/put")
    public Person put(Person person){
        return demoService.save(person);
    }

    @RequestMapping("/able")
    public Optional<Person> cacheable(Person person){
        return demoService.findOne(person);
    }

    @RequestMapping("/evit")
    public String evit(Long id){
        demoService.remove(id);
        return "OK";
    }
}
