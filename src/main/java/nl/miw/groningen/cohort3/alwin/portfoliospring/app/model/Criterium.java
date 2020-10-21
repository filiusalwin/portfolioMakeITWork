package nl.miw.groningen.cohort3.alwin.portfoliospring.app.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alwin Filius
 * Dit is een uitwerking van opdracht:
 * <p>
 * Beschrijving programma
 */

@Entity
public class Criterium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer criteriumId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    private String criteriumDescription;

    private Integer criteriumLevel;

    private Integer points;

    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "criterium")
    private List<Target> targets;

    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "criterium")
    private List<Review> reviews;

    public Integer getCriteriumLevel() {
        return criteriumLevel;
    }

    public void setCriteriumLevel(Integer criteriumLevel) {
        this.criteriumLevel = criteriumLevel;
    }

    public Integer getCriteriumId() {
        return criteriumId;
    }

    public void setCriteriumId(Integer criteriumId) {
        this.criteriumId = criteriumId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCriteriumDescription() {
        return criteriumDescription;
    }

    public void setCriteriumDescription(String criteriumDescription) {
        this.criteriumDescription = criteriumDescription;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
