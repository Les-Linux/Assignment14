package com.elbicon.assignment14.web;

import com.elbicon.assignment14.domain.ChannelServiceImpl;
import com.elbicon.assignment14.domain.RepositoryServiceImpl;
import com.elbicon.assignment14.dto.ChatMessageDto;
import com.elbicon.assignment14.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private RepositoryServiceImpl chatHistory;


    @GetMapping("/{channelId}")
    public String getChatChannel(ModelMap model, @PathVariable Long channelId){
        return "channel";
    }

    @PostMapping("/chat")
    @ResponseBody
    public void updateChannelHistory(@RequestBody ChatMessageDto chatMessage){
        chatHistory.updateChatHistory(Long.valueOf(chatMessage.getChannelId()),chatMessage.getMessage());
    }

    @GetMapping("/chat")
    @ResponseBody
    public String getChannelHistory(Long channelId) {
       List<String> chHistory = chatHistory.getChatHistory(channelId);

       StringBuilder chatHistoryHtml = new StringBuilder();
       chHistory.stream().forEach(entry -> {
           chatHistoryHtml.append("<strong>" +  entry.substring(0,entry.indexOf(":")+1) +
                   "</strong>" + entry.substring(entry.indexOf(":")+1,entry.length()) + "<br />");
       });

       return chatHistoryHtml.toString();
    }

    @GetMapping("/name")
    @ResponseBody
    public String getChannelName(Long channelId){
        return ChannelServiceImpl.getChannelName(channelId);
    }
}
