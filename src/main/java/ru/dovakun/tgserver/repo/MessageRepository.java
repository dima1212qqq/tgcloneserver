package ru.dovakun.tgserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dovakun.tgserver.entity.Message;
import ru.dovakun.tgserver.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiverOrderByTimestampAsc(User sender, User receiver);

    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
            Long senderId1, Long receiverId1, Long senderId2, Long receiverId2);
}