package ch.hearc.stockarc.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.model.Rent;
import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.utils.DateUtils;

@Controller
@EnableWebMvc
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @GetMapping(value = { "/", "/home" })
    public String rent(Model model) {
        Date today = Calendar.getInstance().getTime();

        model.addAttribute("rents", rentRepository.findAllByCreatedAtBetween(DateUtils.getStart(today), DateUtils.getEnd(today), Sort.by(Sort.Order.asc("isOver"), Sort.Order.desc("createdAt"))));
        model.addAttribute("rentsNotOver", rentRepository.findAllWithCreatedAtBefore(DateUtils.getStart(today)));

		return "rent/list";
    }
    
    @PostMapping(value = "/rent/create")
	public String registration(@ModelAttribute Rent rent, BindingResult bindingResult) {

        return "rent/list";
    }

}
