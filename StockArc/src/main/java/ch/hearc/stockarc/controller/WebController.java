package ch.hearc.stockarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.service.SecurityService;
import ch.hearc.stockarc.service.UserService;
import ch.hearc.stockarc.validator.UserValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/user")
	public String user() {
		return "user";
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/403")
	public String Error403() {
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

		// if (bindingResult.hasErrors()) { // <------ ERRORS!!!!!
		// 	return "registration";
		// }

		userService.save(user);

		securityService.autoLogin(user.getName(), user.getPassword());

		return "/";
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
