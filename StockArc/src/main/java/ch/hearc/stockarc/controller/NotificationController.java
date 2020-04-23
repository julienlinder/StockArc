package ch.hearc.stockarc.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ch.hearc.stockarc.model.Notification;
import ch.hearc.stockarc.model.Person;
import ch.hearc.stockarc.repository.NotificationRepository;
import ch.hearc.stockarc.repository.UserRepository;

@Controller
@EnableWebMvc
@RequestMapping("/Notification")
public class NotificationController {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/getNotif")
	public @ResponseBody String displayNotification(Principal principal) throws IOException{        
	    String name = principal.getName();
	    List<Notification> notifications = notificationRepository.findByUser(userRepository.findByName(name));
	    
	    String response = "";
	    
	    for(int i = 0; i<notifications.size(); i++)
	    {
	    	response += notifications.get(i).getMessage() + "\r\n " + notifications.get(i).getCreatedAt().toString() + "\r\n,";
			response += notifications.get(i).isRead();
			if(i+1 != notifications.size())
			{
				response += ",";
			}
	    }
	    
	    return response;
	}
	
	@GetMapping(value="/readNotif")
	public @ResponseBody String readNotificationForUser(Principal principal) throws IOException{        
	    String name = principal.getName();
	    List<Notification> notifications = notificationRepository.findByUser(userRepository.findByName(name));

	    for (Notification notification : notifications) {
			notification.setRead(true);
			notificationRepository.save(notification);
		}
	    
	    return "yes";
	}

}
