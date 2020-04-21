package ch.hearc.stockarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.repository.RentRepository;

@RestController
public class WebController {
	
	@Autowired
    private RentRepository rentRepository;

	/*@RequestMapping(value = "/user")
	public String user() {
		return "user";
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/403")
	public String Error403() {
		return "403";
	}*/
	
	@GetMapping("/")
    public Iterable<Rent> findOne() {
		return rentRepository.findAll();
    }
}
