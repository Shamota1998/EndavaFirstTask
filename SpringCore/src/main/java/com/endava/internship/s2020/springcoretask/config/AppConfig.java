package com.endava.internship.s2020.springcoretask.config;

import com.endava.internship.s2020.springcoretask.enums.SpeedType;
import com.endava.internship.s2020.springcoretask.handryerclasses.HandDryer;
import com.endava.internship.s2020.springcoretask.handryerclasses.Speed;
import com.endava.internship.s2020.springcoretask.handryerclasses.Timer;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:HandDryer.properties")
@EnableCaching
public class AppConfig {
    @Value("${typeOfSpeed}")
    private String speedType;
    @Value("${initialCapacity}")
    private int initialCapacity;
    @Value("${maximumSize}")
    private int maximumSize;
    @Value("${expireAfterAccess}")
    private int expireAfterAccess;

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("validatedInfo");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .expireAfterAccess(expireAfterAccess, TimeUnit.MINUTES));
        return cacheManager;
    }


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
