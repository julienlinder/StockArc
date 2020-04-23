package ch.hearc.stockarc.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ch.hearc.stockarc.model.Notification;

import ch.hearc.stockarc.repository.NotificationRepository;
import ch.hearc.stockarc.repository.UserRepository;

/**
 * Notification controller dispatch all requests concerning notification
 * 
 * @author julien
 *
 */
@Controller
@EnableWebMvc
@RequestMapping("/Notification")
public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * get all the notification of the currently logged user in "message" + "date of
	 * creation" + "," + "isRead" + "," format
	 * 
	 * @param principal the container of the authentication details
	 * @return a string containing all the notification concerning the logged user
	 * 
	 */
	@GetMapping(value = "/getNotif")
	public @ResponseBody String displayNotification(Principal principal) {
		String name = principal.getName();
		List<Notification> notifications = notificationRepository.findByUser(userRepository.findByName(name));

		String response = "";

		for (int i = 0; i < notifications.size(); i++) {
			response += notifications.get(i).getMessage() + "\r\n " + notifications.get(i).getCreatedAt().toString()
					+ "\r\n,";
			response += notifications.get(i).isRead();
			if (i + 1 != notifications.size()) {
				response += ",";
			}
		}

		return response;
	}

	/**
	 * set all actual notifications read state to true it is called each time the
	 * user check his notifications
	 * 
	 * @param principal the container of the authentication details
	 * @return nothing
	 */
	@GetMapping(value = "/readNotif")
	public @ResponseBody String readNotificationForUser(Principal principal) {
		String name = principal.getName();
		List<Notification> notifications = notificationRepository.findByUser(userRepository.findByName(name));

		for (Notification notification : notifications) {
			notification.setRead(true);
			notificationRepository.save(notification);
		}

		return "all_done";
	}

}
