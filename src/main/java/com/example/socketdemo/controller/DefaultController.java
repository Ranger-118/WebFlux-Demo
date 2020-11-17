package com.example.socketdemo.controller;

import java.util.concurrent.ConcurrentHashMap;

import com.example.socketdemo.config.WebSocketSender;
import com.example.socketdemo.service.DefaultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class DefaultController {
	
    @Autowired
    private DefaultService defaultService;
    
	@Autowired
	private ConcurrentHashMap<String, WebSocketSender> senderMap;
 
	@RequestMapping("/send")
	public String sendMessage(@RequestParam String id, @RequestParam String data) {
		WebSocketSender sender = senderMap.get(id);
		defaultService.respond(id);
		if (sender != null) {
			sender.sendData(data);
			return String.format("Message '%s' sent to connection: %s.", data, id);
		} else {
			return String.format("Connection of id '%s' doesn't exist", id);
        }
    }
}
