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
	
	/*@Query("select n from Notification n where n.user.uid=:userId ORDER BY n.createdAt DESC")
	List<Notification> userNotification(@Param("userId") Long userId);*/

	Notification findByUserAndNotificationId(User user,Integer notificationId);
}
