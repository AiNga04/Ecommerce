package hcmute.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // Find a ChatRoom by senderId and recipientId
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
