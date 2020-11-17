package com.example.socketdemo.config;

import java.util.concurrent.ConcurrentHashMap;

import com.example.socketdemo.handler.DefaultHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * WebSocketConfig
 */
@Configuration
public class WebSocketConfig {

    @Autowired
    @Bean
    public HandlerMapping webSocketMapping(final DefaultHandler defaultHandler) {
        return new WebSocketHandlerMapping();
    }

    @Bean
	public ConcurrentHashMap<String, WebSocketSender> senderMap() {
		return new ConcurrentHashMap<String, WebSocketSender>();
	}

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}