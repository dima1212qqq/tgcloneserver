package ru.dovakun.tgserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dovakun.tgserver.entity.Message;
import ru.dovakun.tgserver.entity.User;
import ru.dovakun.tgserver.repo.MessageRepository;
import ru.dovakun.tgserver.repo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message saveMessage(Long senderId, Long receiverId, String content) {
        Optional<User> senderOpt = userRepository.findById(senderId);
        Optional<User> receiverOpt = userRepository.findById(receiverId);

        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            Message message = new Message();
            message.setSender(senderOpt.get());
            message.setReceiver(receiverOpt.get());
            message.setContent(content);
            message.setTimestamp(LocalDateTime.now());
            message.setRead(false);

            return messageRepository.save(message);
        }

        return null;
    }

    public List<Message> getMessageHistory(Long user1Id, Long user2Id) {
        return messageRepository.findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
                user1Id, user2Id, user2Id, user1Id);
    }

}