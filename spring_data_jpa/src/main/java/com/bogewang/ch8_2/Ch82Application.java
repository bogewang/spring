package com.bogewang.ch8_2;

import com.bogewang.ch8_2.support.CustomReposityFactoryBean;
import com.bogewang.ch8_2.support.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomReposityFactoryBean.class)
public class Ch82Application {
    @Autowired
    PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ch82Application.class, args);
	}
}
