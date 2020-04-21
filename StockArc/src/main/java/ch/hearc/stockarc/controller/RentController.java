package ch.hearc.stockarc.controller;

import java.io.Console;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ToolRepository;
import ch.hearc.stockarc.utils.DateUtils;
import ch.hearc.stockarc.validator.RentValidator;

@Controller
@EnableWebMvc
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RentValidator rentValidator;

    @GetMapping(value = { "/", "/home" })
    public String rent(Model model) {
        Date today = Calendar.getInstance().getTime();

        model.addAttribute("rents", rentRepository.findAllByCreatedAtBetween(DateUtils.getStart(today),
                DateUtils.getEnd(today), Sort.by(Sort.Order.asc("isOver"), Sort.Order.desc("createdAt"))));
        model.addAttribute("rentsNotOver", rentRepository.findAllWithCreatedAtBefore(DateUtils.getStart(today)));
        model.addAttribute("tools", toolRepository.findAll());
        model.addAttribute("people", personRepository.findAll());

        return "rent/list";
    }

    @PostMapping(value = "/rent/create")
    public RedirectView registration(@ModelAttribute Rent rent, BindingResult bindingResult) {

        rentValidator.validate(rent, bindingResult);

        if (bindingResult.hasErrors()) {
            return new RedirectView("/");
        }

        rentRepository.save(rent);
        return new RedirectView("/");
    }

    @PostMapping(value = "/rent/update/{id}")
    public RedirectView update(@PathVariable(name = "id") long id, @Valid @ModelAttribute Rent rent,
            BindingResult bindingResult, Model model) {
        Optional<Rent> updatedRent = rentRepository.findById(id);

        if (bindingResult.hasErrors() || !updatedRent.isPresent()) {
            rent.setId(id);
            return new RedirectView("/");
        }

        updatedRent.get().setIsOver(true);
        rentRepository.save(updatedRent.get());
        return new RedirectView("/");
    }
}
