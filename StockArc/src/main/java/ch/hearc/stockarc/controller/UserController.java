package ch.hearc.stockarc.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.service.IUserService;
import ch.hearc.stockarc.service.SecurityService;

@Controller
@EnableWebMvc
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/resetPassword")
    public RedirectView resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
        final User user = userService.findByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), token, user));
        }
        return new RedirectView("/");
    }

    @GetMapping("/changePassword")
    public String showChangePasswordPage(final Model model, @RequestParam("id") final long id,
            @RequestParam("token") final String token) {
        final String result = securityService.validatePasswordResetToken(id, token);
        if (result != null) {

            // Manage fail
            // model.addAttribute("message", messages.getMessage("auth.message." + result,
            // null, locale));
            return "redirect:/login";
        }
        return "redirect:/updatePassword.html";
    }

    @PostMapping("/savePassword")
    public RedirectView savePassword(final Locale locale, @RequestParam("newPassword") String newPassword) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, newPassword);
        SecurityContextHolder.clearContext();
        return new RedirectView("/");
    }

    @GetMapping("/completeAccount")
    public String showCompleteAccountPage(final Model model, @RequestParam("id") final long id,
            @RequestParam("token") final String token) {
        final String result = securityService.validateUserCreationToken(id, token);
        if (result != null) {
            return "redirect:/login";
        }
        return "redirect:/updateAccount.html";
    }

    @PostMapping("/updateAccount")
    public RedirectView updateAccount(final Locale locale, @RequestParam("newPassword") String newPassword,
            @RequestParam("name") String name) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, newPassword);
        userService.changeUserName(user, name);
        SecurityContextHolder.clearContext();
        return new RedirectView("/");
    }

    // ================================================================================
    // Utils
    // ================================================================================

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final String token, final User user) {
        final String url = contextPath + "/users/changePassword?id=" + user.getId() + "&token=" + token;
        return constructEmail("Reset Password", "Follow the link to reset the password \r\n" + url, user);
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