package parsing.relations;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import parsing.entities.Season;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class Course extends PanacheEntity {

    public static final String COMP_524 = "COMP524";
    public static final String COMP_533 = "COMP533";

    private String className;

    @Enumerated(EnumType.STRING)
    private Season season;

    private int year;

    public Course(String className, Season season, int year) {
        this.className = className;
        this.season = season;
        this.year = year;
    }

    public static Course of(String className, Season season, int year) {
        Course course = Course.find("className = ?1 and season = ?2 and year = ?3", className, season, year ).firstResult();
        return Objects.requireNonNullElseGet(course, () -> new Course(className, season, year));
    }

    public Course() {

    }
}