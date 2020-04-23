package ch.hearc.stockarc.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.model.NewUser;
import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.repository.PeopleRepository;
import ch.hearc.stockarc.service.IUserService;

@Controller
@EnableWebMvc
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/createUser")
    public String createUser(Model model) {

        model.addAttribute("people", peopleRepository.findAll(Sort.by(Direction.ASC, "name")));

        return "admin/newaccount";
    }

    @PostMapping("/createUser")
    public String createUserPost(HttpServletRequest request, @Valid @ModelAttribute("newUser") NewUser newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "error";
        }

        User savedUser = userService.createNewPartialUser(newUser);
        if (savedUser != null) {
            final String token = UUID.randomUUID().toString();
            userService.createUserCreationTokenForUser(savedUser, token);
            mailSender.send(construcUserCreationTokenEmail(getAppUrl(request), token, savedUser));
        }
        
        return "admin/newaccount";
    }

    // ================================================================================
    // Utils
    // ================================================================================

    // TODO: Remove duplicate
    private SimpleMailMessage construcUserCreationTokenEmail(final String contextPath, final String token, final User user) {
        final String url = contextPath + "/users/completeAccount?id=" + user.getId() + "&token=" + token;
        return constructEmail("Account information requested", "Follow the link to create your account \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}