package com.endava.internship.s2020.springcoretask;

public enum SpeedType {
    LOW,
    MEDIUM,
    HIGH;

    public static SpeedType speedType(String type){
        if(type.equalsIgnoreCase("Low")){
        return SpeedType.LOW;
    }
        else if(type.equalsIgnoreCase("MEDIUM")){
            return SpeedType.MEDIUM;
        }
        else return SpeedType.HIGH;
    }
}
