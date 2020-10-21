package nl.miw.groningen.cohort3.alwin.portfoliospring.app.controller;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Review;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Target;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CategoryRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.CriteriumRepository;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository.ReviewRepository;
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
public class StudentTargetOverviewController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/studentTarget/{criteriumId}")
    protected String studentTargetSetup(@PathVariable("criteriumId") final Integer criteriumId, Model model) {
        Optional <Criterium> criterium = criteriumRepository.findById(criteriumId);
        model.addAttribute("criterium", new Criterium());

        if (criterium.isPresent()) {
            model.addAttribute("criterium", criterium.get());
            List<Target> targets = targetRepository.findByCriterium(criterium);
            model.addAttribute("allTargets", targets);
            Target target1 = new Target();
            target1.setCriterium(criterium.get());
            model.addAttribute("target", target1);
            Review review = new Review();
            model.addAttribute("review", review);
            review.setCriterium(criterium.get());
            return "studentTargetOverview";
        } else {
            return "redirect:/studentCriteriumOverview";
        }
    }

    @GetMapping("/studentReview/{criteriumId}")
    protected String studentReviewSetup(@PathVariable("criteriumId") final Integer criteriumId, Model model) {
        Optional <Criterium> criterium = criteriumRepository.findById(criteriumId);
        model.addAttribute("criterium", new Criterium());
        model.addAttribute("target", new Target());
        if (criterium.isPresent()) {
            // criterium uitpakken
            model.addAttribute("criterium", criterium.get());

            //target lijst opzoeken en toevoegen aan model + bijbehorend criterium opslaan
            List<Target> targets = targetRepository.findByCriterium(criterium);
            model.addAttribute("allTargets", targets);
            Target target1 = new Target();
            target1.setCriterium(criterium.get());
            model.addAttribute("target", target1);

            // review lijst opzoeken en toevoegen aan model +bijbehorend criterium opslaan
            List<Review> reviews = reviewRepository.findByCriterium(criterium);
            model.addAttribute("allReviews", reviews);
            Review review1 = new Review();
            review1.setCriterium(criterium.get());
            model.addAttribute("review", review1);

            return "studentReviewOverview";
        } else {
            return "redirect:/studentCriteriumOverview";
        }
    }

    @PostMapping("/target/new")
    protected String saveOrUpdateTarget(@ModelAttribute("target") Target target,
                                        @ModelAttribute("criterium") Criterium criterium,
                                        @ModelAttribute("category") Category category,
                                        BindingResult result){
        if (result.hasErrors()){
            return "studentCriteriumOverview";
        } else {
            targetRepository.save(target);
            return "redirect:/studentTarget/" + target.getCriterium().getCriteriumId();
        }
    }


    @PostMapping("/review/new")
    protected String saveOrUpdateReview(@ModelAttribute("review") Review review,
                                        @ModelAttribute("criterium") Criterium criterium,
                                        @ModelAttribute("category") Category category,
                                        BindingResult result){
        if (result.hasErrors()){
            return "studentCriteriumOverview";
        } else {
            reviewRepository.save(review);
            return "redirect:/studentReview/" + review.getCriterium().getCriteriumId();
        }
    }




}
