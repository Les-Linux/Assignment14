package com.elbicon.assignment14.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ChatMessageDto {
    private Long channelId;
    private String message;
}
