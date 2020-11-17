package com.example.socketdemo.service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.example.socketdemo.config.WebSocketSender;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DefaultService {

    private ConcurrentHashMap<String, WebSocketSender> onList = new ConcurrentHashMap<>();

    @Autowired
    private ConcurrentHashMap<String, WebSocketSender> senderMap;

    private String getDateTimeString() {
        return new Date().toString();
    }

    @Async
    public void response(String id) {
        WebSocketSender sender = senderMap.get(id);
        if (ObjectUtils.isEmpty(onList.get(id))) {
            onList.put(id, sender);
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sender.sendData(getDateTimeString());
            }
        }
    }
}
