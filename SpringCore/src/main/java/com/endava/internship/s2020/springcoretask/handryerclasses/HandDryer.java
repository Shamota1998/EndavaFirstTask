package com.endava.internship.s2020.springcoretask.handryerclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HandDryer {
    private Speed speed;
    private Timer timer;
    private Validator validator;

    public String getInfo() {
        return "Current speed = " + speed.getSpeedType() + "\n" + "Time to dry = " + timer.getTimeOfWork();
    }

    @Cacheable("validatedInfo")
    public String getProducerDetails(Producer producer) {
        Set<ConstraintViolation<Producer>> constraintViolations = validator.validate(producer);
        if (!constraintViolations.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<Producer> constraintViolation : constraintViolations) {
                stringBuilder.append(constraintViolation.getMessageTemplate());
            }
            return stringBuilder.toString();
        }
        return "Producer: " + producer.getFirmName() + "\nFoundation year: " + producer.getFoundationYear();
    }
}
