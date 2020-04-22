package ch.hearc.stockarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ToolRepository;

@Controller
@EnableWebMvc
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = { "/" })
    public String people(Model model) {

        return "people/list";
    }
}