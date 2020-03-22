package com.endava.internship.s2020.springcoretask.handryerclasses;

import com.endava.internship.s2020.springcoretask.enums.SpeedType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timer {
    private Speed speed;

    public String getTimeOfWork() {
        if (this.speed.getSpeedType().equals(SpeedType.LOW)) {
            return "60s";
        } else if (this.speed.getSpeedType().equals(SpeedType.MEDIUM)) {
            return "40s";
        } else return "20s";

    }
}
