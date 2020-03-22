package com.endava.internship.s2020.springcoretask.config;

import com.endava.internship.s2020.springcoretask.handryerclasses.HandDryer;
import com.endava.internship.s2020.springcoretask.handryerclasses.Producer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HandDryer handDryer = context.getBean("handDryer", HandDryer.class);
        System.out.println(handDryer.getInfo());

        Producer producer = new Producer("Panasonic", LocalDate.of(2015,12,12));
        System.out.println(handDryer.getProducerDetails(producer));
        System.out.println(handDryer.getProducerDetails(producer));
        System.out.println(handDryer.getProducerDetails(producer));
    }
}
