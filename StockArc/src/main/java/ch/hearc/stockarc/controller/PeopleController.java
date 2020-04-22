package ch.hearc.stockarc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import ch.hearc.stockarc.model.Person;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.SectorRepository;
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

    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping
    public String people(Model model) {

        model.addAttribute("people", personRepository.findAll(Sort.by(Direction.ASC, "name")));
        model.addAttribute("sectors", sectorRepository.findAll(Sort.by(Direction.ASC, "name")));

        return "people/list";
    }

    @PostMapping(value = "/create")
    public RedirectView registration(@Valid @ModelAttribute Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/people");
        }

        personRepository.save(person);
        return new RedirectView("/people");
    }
}