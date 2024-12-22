package hcmute.repository;

import hcmute.entity.Notification;
import hcmute.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    Page<Notification> findByUserEntityAndDeletedIsFalseOrderByCreatedAtDesc(UserEntity userEntity, Pageable pageable);

    Optional<Notification> findByNotificationIdAndUserEntity(UUID notificationId, UserEntity userEntity);
}
