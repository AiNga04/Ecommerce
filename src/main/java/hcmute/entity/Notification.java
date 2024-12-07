package hcmute.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hcmute.model.enums.NotificationState;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
@Builder
public class Notification {
    @Id
    private UUID notificationId;

    private String title;

    @Nationalized
    private String content;

    @Nationalized
    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
//    @ToStringExclude
    private UserEntity userEntity;

    private Date createdAt;

    private Date lastModifiedAt;

    private boolean deleted;

    private NotificationState state = NotificationState.NOT_RECEIVED;
}