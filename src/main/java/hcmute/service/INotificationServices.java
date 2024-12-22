package hcmute.service;

import hcmute.dto.response.PaginationResponse;
import hcmute.model.enums.NotificationState;

import java.util.UUID;

public interface INotificationServices {
    void updateNotificationState(String authorization, UUID notificationId, NotificationState state);

    PaginationResponse getNotifications(String authorization, int page, int limit);
}
