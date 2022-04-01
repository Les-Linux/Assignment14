package com.elbicon.assignment14.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RepositoryService {
    Map<Long, List<String>> chatHistory = new HashMap<>();

    List<String> getChatHistory(Long channelId);
    void updateChatHistory(Long channelId, String chatMessage);
}
