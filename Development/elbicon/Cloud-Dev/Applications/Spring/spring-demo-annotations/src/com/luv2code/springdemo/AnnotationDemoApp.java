package com.luv2code.springdemo;

import com2.luv2code.iface.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.nio.file.attribute.UserPrincipalLookupService;

public class AnnotationDemoApp {
    public static void main(String[] args) {
        // read spring config file
        ClassPathXmlApplicationContext _context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        //get the bean from teh spring container
        Coach theCoach = _context.getBean("myTennisBean", Coach.class);

        //call methods on bean
        System.out.println(theCoach.getDailyWorkout());

        //close container
        _context.close();
    }
}
