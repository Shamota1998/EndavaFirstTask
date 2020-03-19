package com.endava.internship.s2020.springcoretask;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/*To implement Hand Dryer (speed and timer)
 *Using Spring Core. Cache and Validator */
//beanvalidator to inject
//@Component
public class HandDryer {
    private Speed speed;
    private Timer timer;
    private Validator validator;

    // @Autowired
    public HandDryer(Speed speed, Timer timer, Validator validator) {
        this.speed = speed;
        this.timer = timer;
        this.validator = validator;
    }

    // @Cacheable("message")
    public String getInfo() {
        return "Current speed = " + speed.getSpeedType() + "\n" + "Time to dry = " + timer.getTimeOfWork();
    }

    public String getProducerDetails(Producer producer){
        Set<ConstraintViolation<Producer>> constraintViolations = validator.validate(producer);
        if(!constraintViolations.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<ConstraintViolation<Producer>> iter = constraintViolations.iterator();
            while(iter.hasNext()){
                stringBuilder.append(iter.next().getMessageTemplate());
            }
            return stringBuilder.toString();
        }
        return "Producer: " + producer.getFirmName() + "Foundation year: " + producer.getFoundationYear();
    }
}
