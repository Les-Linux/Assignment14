package com.elbicon.assignment14.domain;

import com.elbicon.assignment14.service.ChannelService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class ChannelServiceImpl implements ChannelService {
    private static Map<Long,String> channel = new HashMap<>();

    public static Map<Long,String> getChannels(){
        channel.put(Long.valueOf(1),"General");
        channel.put(Long.valueOf(2),"Support");
        channel.put(Long.valueOf(3), "All Things DeV");

        return channel;
    }

    public static String getChannelName(Long channelId){
        return channel.get(channelId);
    }
}
