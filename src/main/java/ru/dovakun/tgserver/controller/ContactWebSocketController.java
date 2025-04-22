package ru.dovakun.tgserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.dovakun.tgserver.entity.User;
import ru.dovakun.tgserver.repo.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ContactWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    @MessageMapping("/contacts")
    public void sendContacts(@Payload Map<String, Object> payload) {
        System.out.println("Получен запрос на /app/contacts с payload: " + payload);
        List<User> users = userRepository.findAll();
        System.out.println("Отправка пользователей на /topic/contacts: " + users);
        if (users.isEmpty()) {
            System.out.println("ВНИМАНИЕ: Список пользователей пуст!");
        }
        messagingTemplate.convertAndSend("/topic/contacts", users);
    }
}