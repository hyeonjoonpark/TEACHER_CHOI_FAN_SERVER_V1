package bsm.choi.fancafe.domain.notification.repository;

import bsm.choi.fancafe.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiverUuid(UUID receiver);

    List<Notification> findBySenderUuid(UUID uuid);
}