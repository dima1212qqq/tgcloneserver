package ru.dovakun.tgserver.client;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WebSocketSessionManager {
    private final ArrayList<String> activeUsernames = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketSessionManager(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void addUsername(String username) {
        activeUsernames.add(username);
    }
    public void removeUsername(String username) {
        activeUsernames.remove(username);
    }
    public void broadcastActiveUsernames() {
        messagingTemplate.convertAndSend("/app/user", activeUsernames);
        System.out.println("activeUsernames: " + activeUsernames);
    }
}
