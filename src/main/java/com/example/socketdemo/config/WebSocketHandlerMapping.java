package com.example.socketdemo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

public class WebSocketHandlerMapping extends SimpleUrlHandlerMapping {
    
    private Map<String, WebSocketHandler> handlerMap = new HashMap<>();

	/**
     * Register WebSocket handlers annotated by @WebSocketMapping
     * @throws BeansException
     */
    @Override
    public void initApplicationContext() throws BeansException {
        Map<String, Object> beanMap = obtainApplicationContext()
                .getBeansWithAnnotation(WebSocketMapping.class);
        beanMap.values().forEach(bean -> {
            if (!(bean instanceof WebSocketHandler)) {
                throw new RuntimeException(
                        String.format("Controller [%s] doesn't implement WebSocketHandler interface.",
                                bean.getClass().getName()));
            }
            WebSocketMapping annotation = AnnotationUtils.getAnnotation(
                    bean.getClass(), WebSocketMapping.class);
            //webSocketMapping 映射到管理中
            handlerMap.put(Objects.requireNonNull(annotation).value(),(WebSocketHandler) bean);
        });
        super.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.setUrlMap(handlerMap);
        super.initApplicationContext();
    }
}
