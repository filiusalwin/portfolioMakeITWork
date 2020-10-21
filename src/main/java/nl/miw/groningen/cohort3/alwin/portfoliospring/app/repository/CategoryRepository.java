package nl.miw.groningen.cohort3.alwin.portfoliospring.app.repository;

import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Category;
import nl.miw.groningen.cohort3.alwin.portfoliospring.app.model.Criterium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
