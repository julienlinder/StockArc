package ch.hearc.stockarc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.hearc.stockarc.model.Report;
import ch.hearc.stockarc.repository.ReportRepository;

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

    /**
     * Display all the report aka "day with ren".
     * 
     * @param model Model attributes to pass data to the view
     * @return String The views name
     */
    @GetMapping
    public String reports(Model model) {

        model.addAttribute("reports", reportRepository.groupReportByDate());

        return "reports/list";
    }
}