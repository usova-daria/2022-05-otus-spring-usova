package com.otus.spring;

import com.otus.spring.ui.api.ApplicationRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationRunner applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.run();
    }

}
