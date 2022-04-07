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
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private ChannelController channelController;

//    @Autowired
//    ChannelServiceImpl channel;
    @GetMapping("/")
    public String homePage(){
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String getWelcomePage(ModelMap model){
        UserServiceImpl user = new UserServiceImpl();
        //user.setFirstName(" ");

        // add to model
        model.put("user",user);
        //model.put("channel", channel);
        model.put("channel", ChannelServiceImpl.getChannels());

        return "welcome";
    }

    @PostMapping("/welcome/channel")
    public String getChannelPage(UserServiceImpl user,
                                 Long channelId,
                                 RedirectAttributes redirectAttributes,
                                 ModelMap model) {
        try {
            if ((channelId != null) && (user.getFirstName() != null && (!(user.getFirstName().isEmpty())))) {
                /*  RequestAttributes nor the SessionAttributes are an option.
                    That's because the former won't survive a redirection across different controllers,
                    while the latter will last for the entire session even after the form submission is over.

                    Flash attributes are short-lived. As such, these are stored temporarily in some underlying
                    storage, just before the redirect. They remain available for the subsequent request after
                    redirect, and then they're gone.
                 */
                redirectAttributes.addFlashAttribute("firstName", user.getFirstName());
                redirectAttributes.addFlashAttribute("lastName", user.getLastName());
                redirectAttributes.addFlashAttribute("fullName", user.getFirstName() + " " + user.getLastName());
                redirectAttributes.addFlashAttribute("channelId", channelId);
                redirectAttributes.addFlashAttribute("channelName",ChannelServiceImpl.getChannels().get(Long.valueOf(channelId)).toString());

                return "redirect:/channel/" + channelId;

            }
        } catch (Exception e) {
            System.out.println("Exception Caught - " +  e.getMessage());
        }
        return "redirect:/welcome";
    }
}
