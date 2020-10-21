package nl.miw.groningen.cohort3.alwin.portfoliospring.app.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    private String categoryName;


    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "category")
    private List<Criterium> criterium;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Criterium> getCriteriums() {
        return criterium;
    }

    public void setCriteriums(List<Criterium> criteriums) {
        this.criterium = criteriums;
    }
}
