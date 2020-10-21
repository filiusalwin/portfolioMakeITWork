package nl.miw.groningen.cohort3.alwin.portfoliospring.app.controller;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CategoryRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */
@Controller
public class IndexController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TargetRepository targetRepository;

    @GetMapping("/categoryOverview")
    protected String showCategoryOverview(Model model){
        model.addAttribute("allCategory", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "categoryOverview";
    }

    @GetMapping("/studentCategoryOverview")
    protected String showStudentCategoryOverview(Model model){
        model.addAttribute("allCategory", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "studentCategoryOverview";
    }

    @GetMapping("/targetOverview")
    protected String showTargetOverview(Model model){
        model.addAttribute("allTargets", targetRepository.findAll());
        return "targetOverview";
    }




}
