package ch.hearc.stockarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ToolRepository;

/**
 * Tools controller, dispatch all the request concerning tool.
 * 
 * @author Alexandre Bianchi
 */

@Controller
@EnableWebMvc
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Display all the tools.
     * 
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping
    public String tools(Model model) {

        model.addAttribute("tools", toolRepository.findAll(Sort.by(Direction.ASC, "name")));

        return "tools/list";
    }
}