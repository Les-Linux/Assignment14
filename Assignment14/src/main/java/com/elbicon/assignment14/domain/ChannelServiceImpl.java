package com.elbicon.assignment14.domain;

import com.elbicon.assignment14.service.ChannelService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class ChannelServiceImpl implements ChannelService {
    private Map<Long,String> channel = new HashMap<>();
}
