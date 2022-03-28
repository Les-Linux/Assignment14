package com.elbicon.assignment14.web;


import com.elbicon.assignment14.domain.ChannelServiceImpl;
import com.elbicon.assignment14.domain.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    private ChannelController channelController;

    @GetMapping("/welcome")
    public String getWelcomePage(ModelMap model){
        UserServiceImpl user = new UserServiceImpl();
        ChannelServiceImpl channel = new ChannelServiceImpl();


        Map<Long,String> chatChannel = channel.getChannel();
        chatChannel.put(Long.valueOf(1),"General");
        chatChannel.put(Long.valueOf(2),"Support");
        chatChannel.put(Long.valueOf(3), "All Things DeV");

        // channel properties
        channel.setChannel(chatChannel);

        // user properties
        user.setFirstName(" ");

        // add to model
        model.put("user",user);
        model.put("channel", channel);

        return "welcome";
    }

    @PostMapping("/welcome/channel")
    public ModelAndView getChannelPage(UserServiceImpl user, Long channelId, ModelMap model) {
        try {
            if (channelId != null) {
                //ModelMap model = new ModelMap();
                model.put("user", user);
                model.put("channelId", channelId);

                ModelAndView mv = new ModelAndView("redirect:/channel");
                mv.addAllObjects(model);

                //return "redirect:/channel";
                return mv;
            }
        } catch (Exception e) {
            System.out.println("Exception Caught - " +  e.getMessage());
        }
        //return "welcome";
        return new ModelAndView("redirect:/welcome");
    }

    public static RedirectView safeRedirect(String url) {
        RedirectView rv = new RedirectView(url);
        rv.setExposeModelAttributes(false);
        return rv;
    }
}
