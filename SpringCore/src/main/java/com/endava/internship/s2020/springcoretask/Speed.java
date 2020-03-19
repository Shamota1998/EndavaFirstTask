package com.endava.internship.s2020.springcoretask;

import org.springframework.stereotype.Component;

//@Component
public class Speed {
    private SpeedType speedType;

    public Speed() {
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }


}
