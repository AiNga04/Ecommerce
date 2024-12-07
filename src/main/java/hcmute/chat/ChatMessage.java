package hcmute.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the primary key in MySQL
    private Long id;  // Use Long for the primary key with auto-increment

    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private Date timestamp;
}
