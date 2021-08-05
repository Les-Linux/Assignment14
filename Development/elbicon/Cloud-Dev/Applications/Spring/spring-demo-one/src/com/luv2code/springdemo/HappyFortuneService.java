package com.luv2code.springdemo;

import java.util.ArrayList;
import java.util.Random;

public class HappyFortuneService implements FortuneService {
    private ArrayList<String> _Fortune = new ArrayList<String>();
    @Override
    public String getFortune() {

        _Fortune.add("Today is your lucky day");
        _Fortune.add("Tomorrow is your lucky day");
        _Fortune.add("Yesterday was your lucky day");

        Random rand = new Random();
        return _Fortune.get(rand.nextInt(_Fortune.size()));
    }
}
