package nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
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
public interface TargetRepository extends JpaRepository <Target, Integer> {
    List<Target> findByCriterium(Optional<Criterium> criterium);
}
