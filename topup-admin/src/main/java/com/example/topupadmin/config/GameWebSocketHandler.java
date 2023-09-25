package com.example.topupadmin.config;

import org.jboss.logging.Logger;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class GameWebSocketHandler extends TextWebSocketHandler {
    private Logger logger = Logger.getLogger(WebSocket.class);
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        logger.info("Connection established!");
    }
}
