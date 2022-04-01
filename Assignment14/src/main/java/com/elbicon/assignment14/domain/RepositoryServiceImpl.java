package com.elbicon.assignment14.domain;

import com.elbicon.assignment14.service.RepositoryService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class RepositoryServiceImpl implements RepositoryService {

    private static Map<Long, List<String>> chatHistory = new HashMap<>();

    @Override
    public List<String> getChatHistory(Long channelId) {
        final List<String>[] currentChatHistory = new List[]{new ArrayList<>()};
        this.chatHistory.forEach((key,value) -> {
            if(key == channelId){
                currentChatHistory[0] = value;
                return;
            }
        });
        return currentChatHistory[0];
    }

    @Override
    public void updateChatHistory(Long channelId, String chatMessage) {
        if(chatHistory.containsKey(channelId)) {
            chatHistory.forEach((key, value) -> {
                if (key == channelId) {
                    value.add(chatMessage);
                }
            });
        } else{
            List<String> message = new ArrayList<>();
            message.add(chatMessage);
            chatHistory.put(channelId,message);
        }
    }
}
