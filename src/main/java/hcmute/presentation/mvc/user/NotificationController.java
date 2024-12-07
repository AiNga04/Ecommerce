package hcmute.presentation.mvc.user;

import hcmute.dto.response.GeneralResponse;
import hcmute.model.enums.NotificationState;
import hcmute.service.INotificationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notifications")
//@PreAuthorize("hasRole('ROLE_USER')")
@RequiredArgsConstructor
public class NotificationController {
    private final INotificationServices notificationServices;

    @GetMapping("/update-state")
    public ResponseEntity<?> updateNotificationState(
            @RequestParam("Authorization") String authorization,
            @RequestParam("notificationId") UUID notificationId,
            @RequestParam("state") NotificationState state
    ) {
        notificationServices.updateNotificationState(authorization, notificationId, state);
        return ResponseEntity.ok(
                GeneralResponse.builder()
                               .success(true)
                               .message("Update notification state successfully")
                               .data(true)
                               .statusCode(200)
                               .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> getNotifications(
            @RequestParam("Authorization") String authorization,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(
                GeneralResponse.builder()
                               .success(true)
                               .message("Get all notifications successfully")
                               .data(notificationServices.getNotifications(authorization, page, limit))
                               .build()
        );
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(
            @RequestParam("Authorization") String authorization,
            @PathVariable("notificationId") UUID notificationId
    ) {
        notificationServices.updateNotificationState(authorization, notificationId, NotificationState.DELETED);
        return ResponseEntity.ok(
                GeneralResponse.builder()
                               .success(true)
                               .message("Delete notification successfully")
                               .data(true)
                               .statusCode(200)
                               .build()
        );
    }
}
