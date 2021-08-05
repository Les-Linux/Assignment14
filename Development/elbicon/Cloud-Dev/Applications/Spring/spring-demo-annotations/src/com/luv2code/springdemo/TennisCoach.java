package com.luv2code.springdemo;
import com2.luv2code.iface.Coach;
import org.springframework.stereotype.Component;

@Component("myTennisBean") // myTennisBean = Bean in
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkout(){
        return "Practice your Tennis Backhand Volley";
    }
}
