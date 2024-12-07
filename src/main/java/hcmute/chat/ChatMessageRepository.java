package hcmute.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // Find chat messages by chatId
    List<ChatMessage> findByChatId(String chatId);
}
