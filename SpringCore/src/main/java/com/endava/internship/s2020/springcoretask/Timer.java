package com.endava.internship.s2020.springcoretask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Timer {
    private Speed speed;

    public Timer(Speed speed) {
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public String getTimeOfWork(){
        if(this.speed.getSpeedType().equals(SpeedType.LOW)){
            return "60s";
        }
        else if(this.speed.getSpeedType().equals(SpeedType.MEDIUM)){
            return "40s";
        }
        else return "20s";

    }
}
