package ch.hearc.stockarc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.hearc.stockarc.model.Notification;
import ch.hearc.stockarc.model.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findByUser(User user);

	Notification findByUserAndNotificationId(User user,Integer notificationId);
}
