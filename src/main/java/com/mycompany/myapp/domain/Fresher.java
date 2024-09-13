package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Fresher.
 */
@Entity
@Table(name = "fresher")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fresher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "programming_language", nullable = false)
    private String programmingLanguage;

    @Column(name = "assignment_1_score")
    private Float assignment1Score;

    @Column(name = "assignment_2_score")
    private Float assignment2Score;

    @Column(name = "assignment_3_score")
    private Float assignment3Score;

    @Column(name = "final_score")
    private Float finalScore;

    @ManyToOne
    @JsonIgnoreProperties(value = { "freshers", "projects" }, allowSetters = true)
    private Center center;

    @ManyToMany(mappedBy = "freshers")
    @JsonIgnoreProperties(value = { "freshers", "center" }, allowSetters = true)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fresher id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Fresher name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public Fresher email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgrammingLanguage() {
        return this.programmingLanguage;
    }

    public Fresher programmingLanguage(String programmingLanguage) {
        this.setProgrammingLanguage(programmingLanguage);
        return this;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public Float getAssignment1Score() {
        return this.assignment1Score;
    }

    public Fresher assignment1Score(Float assignment1Score) {
        this.setAssignment1Score(assignment1Score);
        return this;
    }

    public void setAssignment1Score(Float assignment1Score) {
        this.assignment1Score = assignment1Score;
    }

    public Float getAssignment2Score() {
        return this.assignment2Score;
    }

    public Fresher assignment2Score(Float assignment2Score) {
        this.setAssignment2Score(assignment2Score);
        return this;
    }

    public void setAssignment2Score(Float assignment2Score) {
        this.assignment2Score = assignment2Score;
    }

    public Float getAssignment3Score() {
        return this.assignment3Score;
    }

    public Fresher assignment3Score(Float assignment3Score) {
        this.setAssignment3Score(assignment3Score);
        return this;
    }

    public void setAssignment3Score(Float assignment3Score) {
        this.assignment3Score = assignment3Score;
    }

    public Float getFinalScore() {
        return this.finalScore;
    }

    public Fresher finalScore(Float finalScore) {
        this.setFinalScore(finalScore);
        return this;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public Center getCenter() {
        return this.center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Fresher center(Center center) {
        this.setCenter(center);
        return this;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.removeFreshers(this));
        }
        if (projects != null) {
            projects.forEach(i -> i.addFreshers(this));
        }
        this.projects = projects;
    }

    public Fresher projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public Fresher addProjects(Project project) {
        this.projects.add(project);
        project.getFreshers().add(this);
        return this;
    }

    public Fresher removeProjects(Project project) {
        this.projects.remove(project);
        project.getFreshers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fresher)) {
            return false;
        }
        return id != null && id.equals(((Fresher) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fresher{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", programmingLanguage='" + getProgrammingLanguage() + "'" +
            ", assignment1Score=" + getAssignment1Score() +
            ", assignment2Score=" + getAssignment2Score() +
            ", assignment3Score=" + getAssignment3Score() +
            ", finalScore=" + getFinalScore() +
            "}";
    }
}
