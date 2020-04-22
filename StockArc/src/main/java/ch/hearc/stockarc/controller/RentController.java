package ch.hearc.stockarc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import ch.hearc.stockarc.model.Tool;
import ch.hearc.stockarc.model.Tool.Type;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ToolRepository;
import ch.hearc.stockarc.utils.DateUtils;
import ch.hearc.stockarc.validator.RentValidator;

/**
 * Rent controller, dispatch all the request concerning rent.
 * 
 * @author Alexandre Bianchi
 */

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

    /**
     * Display all the current rent of the day and the old rent that are not closed.
     * 
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
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

    /**
     * Create a new rent from the posted data.
     * 
     * @param rent          The rent object
     * @param bindingResult Represent the binding result
     * @param request       Represent the HttpServletRequest
     * @return RedirectView The view shown after processing
     */
    @PostMapping(value = "/rent/create")
    public RedirectView registration(@ModelAttribute Rent rent, BindingResult bindingResult,
            HttpServletRequest request) {

        String referer = request.getHeader("Referer");

        rentValidator.validate(rent, bindingResult);

        if (bindingResult.hasErrors()) {
            return new RedirectView(referer);
        }

        // If the item is disposable we decrement the quantity and mark the rent as over.
        if (rent.getTool().getType() == Type.DISPOSABLE) {
            Tool tool = rent.getTool();
            tool.setQuantity(tool.getQuantity() - rent.getQuantity());
            rent.setIsOver(true);

            toolRepository.save(tool);
        }
        rentRepository.save(rent);
        return new RedirectView(referer);
    }

    /**
     * Update a rent with the submitted data
     * 
     * @param id            Id of the object that we want to update
     * @param rent          The rent object
     * @param bindingResult Represent the binding result
     * @param model         Model attributes to pass data to the view
     * @return RedirectView The view shown after processing
     */
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
