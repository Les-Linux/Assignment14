package com.luv2code.springdemo;

public class SportingClay implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice 100 Birds";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }
}
