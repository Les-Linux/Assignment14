package com.luv2code.springdemo;

import org.springframework.web.servlet.tags.HtmlEscapeTag;

public class CricketCoach implements Coach {
    private FortuneService fortuneService;
    private String emailAddress;
    private String team;

    //no-arg constructor
    public CricketCoach(){}

    //Constructor
    public CricketCoach(FortuneService fvFortuneService){
        fortuneService = fvFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on bowling practice";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }


    //Setter Section
    public void setFortuneService(FortuneService fvFortuneService){
        System.out.println("Cricket Coach inside setter method");
        this.fortuneService = fvFortuneService;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTeam() {
        return team;
    }


    public void setTeam(String team) {
        this.team = team;
    }


    //add a class init method
    public void doMyStartupStuff(){
        System.out.println("Cricket Coach: class init method called");
    }

    //add a class destroy method
    public void doMyDestroyStuff(){
        System.out.println("Cricket Coach: class destroy method called");
    }
}
