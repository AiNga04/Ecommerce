package hcmute.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hcmute.entity.UserEntity;
import hcmute.model.enums.NotificationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String title;

    private String content;

    private String data;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
//    @ToStringExclude
    private UserEntity userEntity;

    private Date createdAt;

    private Date lastModifiedAt;

    private boolean deleted;

    private NotificationState state = NotificationState.NOT_RECEIVED;
}