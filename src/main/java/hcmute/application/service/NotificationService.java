package hcmute.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    //    @Scheduled(fixedRate = 5000)
    public void sendNotification() {
        log.info("Sending notification to");
        String message = "Hello, this is a notification";
        messagingTemplate.convertAndSend("/notification", message);
    }

    public void sendNotification(String userId, Notification notification) {
        log.info("Sending WS notification to {} with payload {}", userId, notification);
        messagingTemplate.convertAndSendToUser(
                userId,
                "/notifications",
                notification
        );
    }
}