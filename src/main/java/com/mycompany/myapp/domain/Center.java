package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Center.
 */
@Entity
@Table(name = "center")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "center")
    @JsonIgnoreProperties(value = { "center", "projects" }, allowSetters = true)
    private Set<Fresher> freshers = new HashSet<>();

    @OneToMany(mappedBy = "center")
    @JsonIgnoreProperties(value = { "freshers", "center" }, allowSetters = true)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Center id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Center name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public Center location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public Center description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Fresher> getFreshers() {
        return this.freshers;
    }

    public void setFreshers(Set<Fresher> freshers) {
        if (this.freshers != null) {
            this.freshers.forEach(i -> i.setCenter(null));
        }
        if (freshers != null) {
            freshers.forEach(i -> i.setCenter(this));
        }
        this.freshers = freshers;
    }

    public Center freshers(Set<Fresher> freshers) {
        this.setFreshers(freshers);
        return this;
    }

    public Center addFreshers(Fresher fresher) {
        this.freshers.add(fresher);
        fresher.setCenter(this);
        return this;
    }

    public Center removeFreshers(Fresher fresher) {
        this.freshers.remove(fresher);
        fresher.setCenter(null);
        return this;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.setCenter(null));
        }
        if (projects != null) {
            projects.forEach(i -> i.setCenter(this));
        }
        this.projects = projects;
    }

    public Center projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public Center addProjects(Project project) {
        this.projects.add(project);
        project.setCenter(this);
        return this;
    }

    public Center removeProjects(Project project) {
        this.projects.remove(project);
        project.setCenter(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Center)) {
            return false;
        }
        return id != null && id.equals(((Center) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Center{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
