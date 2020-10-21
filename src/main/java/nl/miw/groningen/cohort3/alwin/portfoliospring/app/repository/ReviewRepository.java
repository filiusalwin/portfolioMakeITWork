package nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Review;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */
public interface ReviewRepository extends JpaRepository <Review, Integer> {
    List<Review> findByCriterium(Optional<Criterium> criterium);
}
