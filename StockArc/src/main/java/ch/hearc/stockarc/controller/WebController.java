package ch.hearc.stockarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.service.SecurityService;
import ch.hearc.stockarc.service.UserService;
import ch.hearc.stockarc.validator.UserValidator;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping(value = { "/", "/home" })
	public String home() {
		return "home";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "user";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping(value = "/403")
	public String error403() {
		return "403";
	}

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(user);

		// securityService.autoLogin(user.getName(), user.getPassword());

		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

}
