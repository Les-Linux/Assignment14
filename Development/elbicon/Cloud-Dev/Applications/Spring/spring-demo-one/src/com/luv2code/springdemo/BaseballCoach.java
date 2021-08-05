package com.luv2code.springdemo;

public class BaseballCoach implements Coach {
    private FortuneService fortuneService;

    //Constructor
    public BaseballCoach(FortuneService fvFortuneService){
        fortuneService = fvFortuneService;
    }
    @Override
    public String getDailyWorkout(){
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }


}
