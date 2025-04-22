package ru.dovakun.tgserver.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private String type;
    private Long senderId;
    private Long receiverId;
    private String content;
    private Long userId;
    private Long contactId;
}