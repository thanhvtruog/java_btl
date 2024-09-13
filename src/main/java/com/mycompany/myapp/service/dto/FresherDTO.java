package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Fresher} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FresherDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String programmingLanguage;

    private Float assignment1Score;

    private Float assignment2Score;

    private Float assignment3Score;

    private Float finalScore;

    private CenterDTO center;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public Float getAssignment1Score() {
        return assignment1Score;
    }

    public void setAssignment1Score(Float assignment1Score) {
        this.assignment1Score = assignment1Score;
    }

    public Float getAssignment2Score() {
        return assignment2Score;
    }

    public void setAssignment2Score(Float assignment2Score) {
        this.assignment2Score = assignment2Score;
    }

    public Float getAssignment3Score() {
        return assignment3Score;
    }

    public void setAssignment3Score(Float assignment3Score) {
        this.assignment3Score = assignment3Score;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }

    public CenterDTO getCenter() {
        return center;
    }

    public void setCenter(CenterDTO center) {
        this.center = center;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FresherDTO)) {
            return false;
        }

        FresherDTO fresherDTO = (FresherDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fresherDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FresherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", programmingLanguage='" + getProgrammingLanguage() + "'" +
            ", assignment1Score=" + getAssignment1Score() +
            ", assignment2Score=" + getAssignment2Score() +
            ", assignment3Score=" + getAssignment3Score() +
            ", finalScore=" + getFinalScore() +
            ", center=" + getCenter() +
            "}";
    }
}
