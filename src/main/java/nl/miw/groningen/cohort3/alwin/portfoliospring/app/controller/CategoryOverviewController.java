package nl.miw.groningen.cohort3.alwin.portfoliospring.app.controller;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CategoryRepository;
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
public class CategoryOverviewController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    protected String showCategory(Model model){
        model.addAttribute("allCategory", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "categoryOverview";
    }

    @PostMapping("/category/new")
    protected String saveOrUpdateCategory(@ModelAttribute("category")Category category, BindingResult result){
        if (result.hasErrors()){
            return "categoryOverview";
        } else {
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }

    @GetMapping("/category/delete/{categoryId}")
    protected String deleteCategory(@ModelAttribute("category")Category category, BindingResult result){
        categoryRepository.deleteById(category.getCategoryId());
        return "redirect:/categoryOverview";
    }


}
