package hcmute.service.impl;

import hcmute.dto.request.NotificationRequest;
import hcmute.dto.response.PaginationResponse;
import hcmute.entity.Notification;
import hcmute.entity.UserEntity;
import hcmute.exception.ResourceNotFoundException;
import hcmute.model.enums.NotificationState;
import hcmute.repository.NotificationRepository;
import hcmute.service.INotificationServices;
import hcmute.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements INotificationServices {
    private final SimpMessagingTemplate messagingTemplate;
    private final IUserService userService;
    private final NotificationRepository notificationRepository;

    //    @Scheduled(fixedRate = 5000)
    public void sendNotification() {
        log.info("Sending notification to");
        String message = "Hello, this is a notification";
        messagingTemplate.convertAndSend("/notification", message);
    }

    public void sendNotification(String userId, NotificationRequest notification) {
        log.info("Sending WS notification to {} with payload {}", userId, notification);
        messagingTemplate.convertAndSendToUser(
                userId,
                "/notifications",
                notification
        );
        UserEntity userEntity = userService.findById(Integer.parseInt(userId))
                                           .orElseThrow(() -> new RuntimeException("User not found"));

        notificationRepository.save(Notification.builder()
                                                .notificationId(UUID.randomUUID())
                                                .title("Update status order")
                                                .content(notification.getContent())
                                                .data("Order " + notification.getOrderId() + " has been updated")
                                                .userEntity(userEntity)
                                                .state(NotificationState.NOT_RECEIVED)
                                                .build());
    }

    @Override
    public void updateNotificationState(String authorization, UUID notificationId, NotificationState state) {
        log.info("Update notification state {} {} {}", authorization, notificationId, state);
//        Account account = accountServices.findAccountByToken(authorization);
        UserEntity userEntity = userService.findByUsername(authorization)
                                           .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Notification> notificationOptional = notificationRepository
                .findByNotificationIdAndUserEntity(notificationId, userEntity);

        if (notificationOptional.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Notification not found" + notificationId,
                    "notificationId",
                    notificationId
            );
        }

        Notification notification = notificationOptional.get();
        notification.setState(state);

        if (state == NotificationState.DELETED) {
            notification.setDeleted(true);
        }
        notificationRepository.save(notification);
    }

    @Override
    public PaginationResponse getNotifications(String authorization, int page, int limit) {
        log.info("Get notifications {} {} {}", authorization, page, limit);
//        Account account = accountServices.findAccountByToken(authorization);
        UserEntity userEntity = userService.findById(Integer.parseInt(authorization))
                                           .orElseThrow(() -> new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Notification> notifications = notificationRepository.findByUserEntityAndDeletedIsFalseOrderByCreatedAtDesc(
                userEntity,
                pageable
        );
        return PaginationResponse.builder()
                                 .limit(limit)
                                 .page(page)
                                 .totalItems((int) notifications.getTotalElements())
                                 .items(notifications.getContent())
                                 .build();
    }
}