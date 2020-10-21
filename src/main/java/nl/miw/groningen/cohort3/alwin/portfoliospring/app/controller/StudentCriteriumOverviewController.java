package nl.miw.groningen.cohort3.alwin.portfoliospring.app.controller;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Target;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CategoryRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CriteriumRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */
@Controller
public class StudentCriteriumOverviewController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    TargetRepository targetRepository;

    @GetMapping("/studentCriterium/{categoryId}")
    protected String studentCriteriumSetup(@PathVariable("categoryId") final Integer categoryId, Model model) {
        model.addAttribute("allCategory", categoryRepository.findAll());
        model.addAttribute("target", new Target());
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            List<Criterium> criterium = criteriumRepository.findByCategory(category.get());
            model.addAttribute("allCriterium", criterium);
            model.addAttribute("category", category.get());
            Criterium criterium1 = new Criterium();
            criterium1.setCategory(category.get());
            model.addAttribute("criterium", criterium1);
            return "studentCriteriumOverview";
        } else {
            return "redirect:/studentCriteriumOverview";
        }
    }




}
