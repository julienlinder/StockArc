package ch.hearc.stockarc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.repository.RentRepository;
import ch.hearc.stockarc.repository.ReportRepository;
import ch.hearc.stockarc.utils.DateUtils;

/**
 * Report controller, dispatch all the request concerning report.
 * 
 * @author Alexandre Bianchi
 */

@Controller
@EnableWebMvc
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RentRepository rentRepository;

    /**
     * Display all the report aka "day with rents".
     * 
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping
    public String reports(Model model) {

        model.addAttribute("reports", reportRepository.groupReportByDate());

        return "reports/list";
    }

    /**
     * Show the rents of one report.
     * 
     * @param day   The day of the report
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping("/{day}")
    public String showUnique(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") Date day, Model model) {

        Date start = DateUtils.getStart(day);
        Date end = DateUtils.getEnd(day);

        model.addAttribute("rents",
                rentRepository.findAllByCreatedAtBetween(start, end, Sort.by(Direction.DESC, "createdAt")));
        model.addAttribute("day", day);

        return "reports/unique";
    }

}