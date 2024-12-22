package hcmute.presentation.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping("/chat")
public class NotificationActivity {
    @MessageMapping("/lobby/{lobbyId}/message") // client send message to /app/chat/lobby/{lobbyId}/message
    @SendTo("/chat/lobby/{lobbyId}/message/data") // server send message to /topic/chat/lobby/{lobbyId}/message/data
    public String joinLobby(
            @Payload String message
//            DestinationVariable String lobbyId,
    ) {
        return message;
    }
}
