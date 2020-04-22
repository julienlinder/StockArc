package ch.hearc.stockarc.controller;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.model.Tool;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ToolRepository;
import ch.hearc.stockarc.utils.DateUtils;

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

    /**
     * Show the rent of one tool.
     * 
     * @param id    The id of the person
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping("/{id}")
    public String showUnique(@PathVariable("id") long id, Model model) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tool Id:" + id));

        Date today = Calendar.getInstance().getTime();
        Date start = DateUtils.getStart(today);
        Date end = DateUtils.getEnd(today);

        model.addAttribute("tool", tool);
        model.addAttribute("rents", tool.getRents());
        model.addAttribute("rents",
                tool.getRents().stream()
                        .filter(r -> start.compareTo(r.getCreatedAt()) * r.getCreatedAt().compareTo(end) > 0)
                        .filter(r -> !r.getIsOver()).sorted(Comparator.comparing(Rent::getCreatedAt).reversed())
                        .collect(Collectors.toList()));
        model.addAttribute("people", personRepository.findAll());
        model.addAttribute("closedRents", tool.getRents().stream().filter(r -> r.getIsOver())
                .sorted(Comparator.comparing(Rent::getCreatedAt).reversed()).collect(Collectors.toList()));

        return "tools/unique";
    }

    /**
     * Create a new tool from the posted data.
     * 
     * @param person        The tool object
     * @param bindingResult Represent the binding result
     * @return RedirectView The view shown after processing
     */
    @PostMapping(value = "/create")
    public RedirectView store(@Valid @ModelAttribute Tool tool, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/tools");
        }

        toolRepository.save(tool);
        return new RedirectView("/tools");
    }
}