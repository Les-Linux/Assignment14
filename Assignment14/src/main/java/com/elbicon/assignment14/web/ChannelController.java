package com.elbicon.assignment14.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChannelController {

    @GetMapping("/channel")
    public String getChatChannel(ModelMap modelMap){

        return "channel";
    }
}
