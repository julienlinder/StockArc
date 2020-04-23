package ch.hearc.stockarc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represent a Notification
 * 
 * @author julien
 *
 */

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer notificationId;

	private String message;
	
	private Date createdAt;
	
	private boolean isRead;
	
	@ManyToOne
	private User user;
	
	public Notification(){}
	
	public Notification(String message, Date createdAt, User user)
	{
		this.message = message;
		this.createdAt = createdAt;
		this.user = user;
		this.isRead = false;
	}

	/**
	 * get the id of the notification
	 * 
	 * @return the id of the notification
	 */
	public Integer getNotificationId() {
		return notificationId;
	}

	/**
	 * set the id of the notification
	 * 
	 * @param notificationId the new id
	 */
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * get the message of the notification
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * set the message of the notification
	 * 
	 * @param message the message of the notification
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * get the date of creation of the notification
	 * 
	 * @return the date of creation
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * set the date of creation of the notification
	 * 
	 * @param createdAt the date of creation
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * if the notification was read by the user
	 * 
	 * @return true if the user has read the notification false otherwise
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * set the read state of the notification
	 * 
	 * @param isRead the new state
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * get the user who is concerned by this notification
	 * 
	 * @return the user concerned
	 */
	public User getUser() {
		return user;
	}

	/**
	 * set the user that is concerned by this notification
	 * 
	 * @param user the new user concerned
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
