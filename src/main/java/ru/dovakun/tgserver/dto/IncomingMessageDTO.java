package ru.dovakun.tgserver.dto;

import lombok.Data;

@Data
public class IncomingMessageDTO {
    private String type;
    private Long senderId;
    private Long receiverId;
    private String content;
}