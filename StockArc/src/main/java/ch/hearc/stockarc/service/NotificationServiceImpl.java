package ch.hearc.stockarc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.stockarc.model.Notification;
import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public NotificationServiceImpl() {}
	
	@Override
	public Notification save(Notification notif)
	{
		try
		{
			return notificationRepository.save(notif);
			//return null;
		} catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Notification> findByUser(User user)
	{
		try
		{
			return notificationRepository.findByUser(user);
			//return null;
		} catch(Exception e)
		{
			return null;
		}
	}
	
	@Override
	public List<Notification> findByUser(User user, Integer limit)
	{
		try
		{
			//return notificationRepository.userNotification(user.getId());
			return null;
		} catch(Exception e)
		{
			return null;
		}
	}
	
	@Override
	public Notification createNotificationObject(String message,User user)
	{
		return new Notification(message,new Date(),user);
	}
	
	@Override
	public Notification findByUserAndNotificationId(User user,Integer notificationId)
	{
		try{
			return notificationRepository.findByUserAndNotificationId(user,notificationId);
		}catch (Exception e) {
			return null;
		}
	}
}
