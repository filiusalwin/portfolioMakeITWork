package nl.miw.groningen.cohort3.alwin.portfoliospring.app.controller;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CategoryRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CriteriumRepository;
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
public class CriteriumOverviewController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CriteriumRepository criteriumRepository;

    @GetMapping("/criterium/{categoryId}")
    protected String createBuzzword(@PathVariable("categoryId") final Integer categoryId, Model model) {
        model.addAttribute("allCategory", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            List<Criterium> criterium = criteriumRepository.findByCategory(category.get());
            model.addAttribute("allCriterium", criterium);
            model.addAttribute("category", category.get());
            Criterium criterium1 = new Criterium();
            criterium1.setCategory(category.get());
            model.addAttribute("criterium", criterium1);
            return "criteriumOverview";
        } else {
            return "redirect:/criteriumOverview";
        }
    }

    @GetMapping("/criterium/delete/{criteriumId}")
    protected String deleteCategory(@PathVariable("criteriumId") final Integer criteriumId){
        Optional<Criterium> criterium1 = criteriumRepository.findById(criteriumId);
        if (criterium1.isPresent()) {
            int categoryId = criterium1.get().getCategory().getCategoryId();
            criteriumRepository.delete(criterium1.get());
            return "redirect:/criterium/" + categoryId;
        }

        return "forward:/categoryOverview";
    }

    @PostMapping("/criterium/new")
    protected String saveOrUpdateCriterium(@ModelAttribute("criterium")Criterium criterium, BindingResult result){
        if (result.hasErrors()){
            return "criteriumOverview";
        } else {
            criteriumRepository.save(criterium);
            return "redirect:/criterium/" + criterium.getCategory().getCategoryId();
        }
    }
}
