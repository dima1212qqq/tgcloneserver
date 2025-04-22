package ru.dovakun.tgserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.dovakun.tgserver.client.WebSocketSessionManager;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final WebSocketSessionManager sessionManager;

    public WebSocketController(SimpMessagingTemplate messagingTemplate, WebSocketSessionManager sessionManager) {
        this.messagingTemplate = messagingTemplate;
        this.sessionManager = sessionManager;
    }
    @MessageMapping("/connect")
    public void connectUser(String username) {
        sessionManager.addUsername(username);
        sessionManager.broadcastActiveUsernames();
        System.out.println("Connected: " + username);
    }
    @MessageMapping("disconnect")
    public void disconnectUser(String username) {
        sessionManager.removeUsername(username);
        sessionManager.broadcastActiveUsernames();
        System.out.println("Disconnected: " + username);
    }

}
