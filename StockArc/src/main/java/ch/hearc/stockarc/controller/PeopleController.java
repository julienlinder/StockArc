package ch.hearc.stockarc.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import ch.hearc.stockarc.model.Person;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.SectorRepository;
import ch.hearc.stockarc.repository.ToolRepository;
import ch.hearc.stockarc.utils.DateUtils;

/**
 * People controller, dispatch all the request concerning person.
 * 
 * @author Alexandre Bianchi
 */

@Controller
@EnableWebMvc
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SectorRepository sectorRepository;

    /**
     * Display all the people.
     * 
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping
    public String people(Model model) {

        model.addAttribute("people", personRepository.findAll(Sort.by(Direction.ASC, "name")));
        model.addAttribute("sectors", sectorRepository.findAll(Sort.by(Direction.ASC, "name")));

        return "people/list";
    }

    /**
     * Show the rent of one person.
     * 
     * @param id    The id of the person
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping("/{id}")
    public String showUnique(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        Date today = Calendar.getInstance().getTime();
        Date start = DateUtils.getStart(today);
        Date end = DateUtils.getEnd(today);

        model.addAttribute("person", person);
        model.addAttribute("rents",
                person.getRents().stream()
                        .filter(r -> start.compareTo(r.getCreatedAt()) * r.getCreatedAt().compareTo(end) > 0)
                        .filter(r -> !r.getIsOver()).collect(Collectors.toSet()));
        model.addAttribute("tools", toolRepository.findAll());
        model.addAttribute("closedRents",
                person.getRents().stream().filter(r -> r.getIsOver()).collect(Collectors.toSet()));

        return "people/unique";
    }

    /**
     * Create a new person from the posted data.
     * 
     * @param person        The person object
     * @param bindingResult Represent the binding result
     * @return RedirectView The view shown after processing
     */
    @PostMapping(value = "/create")
    public RedirectView store(@Valid @ModelAttribute Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/people");
        }

        personRepository.save(person);
        return new RedirectView("/people");
    }
}