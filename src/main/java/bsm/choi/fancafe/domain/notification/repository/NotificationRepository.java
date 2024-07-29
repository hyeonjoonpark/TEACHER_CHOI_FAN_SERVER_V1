package bsm.choi.fancafe.domain.notification.repository;

import bsm.choi.fancafe.domain.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}