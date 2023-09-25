package com.example.topupadmin.config;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocket
@Controller
public class WebSocket  implements WebSocketConfigurer {
    private Logger logger = Logger.getLogger(WebSocket.class);
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gameWebSocketHandler(), "/chat");
    }

    @Bean
    public WebSocketHandler gameWebSocketHandler() {
        return new GameWebSocketHandler();
    }

}
