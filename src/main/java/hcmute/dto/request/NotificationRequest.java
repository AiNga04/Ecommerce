package hcmute.dto.request;

import hcmute.model.enums.NotificationState;
import hcmute.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private String title;

    private String content;

    private String data;

    private String orderId;

    private Status status;

    private Date createdAt;

    private Date lastModifiedAt;

    private boolean deleted;

    private NotificationState state = NotificationState.NOT_RECEIVED;
}