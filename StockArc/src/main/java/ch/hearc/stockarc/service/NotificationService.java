package ch.hearc.stockarc.service;

import java.util.List;

import ch.hearc.stockarc.model.Notification;
import ch.hearc.stockarc.model.User;

public interface NotificationService {
	public Notification save(Notification notif);
	
	public List<Notification> findByUser(User user);
	
	public List<Notification> findByUser(User user, Integer limit);
	
	public Notification createNotificationObject(String message,User user);
	
	public Notification findByUserAndNotificationId(User user,Integer notificationId);
}
