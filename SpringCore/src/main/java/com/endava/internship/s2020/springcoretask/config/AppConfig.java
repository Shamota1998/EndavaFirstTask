package com.endava.internship.s2020.springcoretask.config;

import com.endava.internship.s2020.springcoretask.HandDryer;
import com.endava.internship.s2020.springcoretask.Speed;
import com.endava.internship.s2020.springcoretask.SpeedType;
import com.endava.internship.s2020.springcoretask.Timer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;


@Configuration
@PropertySource("classpath:HandDryer.properties")
//@EnableCaching
public class AppConfig {
    @Value("${typeOfSpeed}")
    private String speedType;


    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singletonList(
                new ConcurrentMapCache("message")
        ));
        return cacheManager;
    }


    //program Args
    //string to SpeedType(+method)
    @Bean
    public Speed speed(){
        Speed speed = new Speed();
        speed.setSpeedType(SpeedType.speedType(speedType));
        return speed;
    }

    @Bean
    public Timer timer(Speed speed){
        return new Timer(speed);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HandDryer handDryer(Speed speed, Timer timer, Validator validator){
        return new HandDryer(speed, timer, validator);
    }
}
