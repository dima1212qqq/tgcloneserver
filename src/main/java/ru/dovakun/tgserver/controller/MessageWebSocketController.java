package ru.dovakun.tgserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.dovakun.tgserver.dto.ChatMessage;
import ru.dovakun.tgserver.dto.MessageDTO;
import ru.dovakun.tgserver.entity.Message;
import ru.dovakun.tgserver.service.MessageService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MessageWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/message")
    public void processMessage(@Payload ChatMessage request) {
        try {
            if ("message".equals(request.getType())) {
                Long senderId = request.getSenderId();
                Long receiverId = request.getReceiverId();
                String content = request.getContent();

                Message message = messageService.saveMessage(senderId, receiverId, content);

                if (message != null) {
                    MessageDTO messageDTO = convertToDTO(message);

                    System.out.println("Отправка сообщения в /user/" + receiverId + "/topic/messages: " + messageDTO);
                    messagingTemplate.convertAndSendToUser(
                            receiverId.toString(),
                            "/topic/messages",
                            messageDTO,
                            Map.of("content-type", "application/json")
                    );

                    System.out.println("Отправка сообщения в /user/" + senderId + "/topic/messages: " + messageDTO);
                    messagingTemplate.convertAndSendToUser(
                            senderId.toString(),
                            "/topic/messages",
                            messageDTO,
                            Map.of("content-type", "application/json")
                    );
                }
            } else if ("history".equals(request.getType())) {
                Long userId = request.getUserId();
                Long contactId = request.getContactId();
                List<Message> messages = messageService.getMessageHistory(userId, contactId);
                List<MessageDTO> messageDTOs = messages.stream().map(this::convertToDTO).collect(Collectors.toList());
                Map<String, Object> historyResponse = Map.of(
                        "type", "history",
                        "messages", messageDTOs
                );
                System.out.println("Отправка истории в /user/" + userId + "/topic/messages: " + historyResponse);
                messagingTemplate.convertAndSendToUser(
                        userId.toString(),
                        "/topic/messages",
                        historyResponse,
                        Map.of("content-type", "application/json")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setTimestamp(message.getTimestamp());
        dto.setRead(message.isRead());
        return dto;
    }
}