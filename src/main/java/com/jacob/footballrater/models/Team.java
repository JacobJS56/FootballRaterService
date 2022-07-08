package com.jacob.footballrater.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="team")
public class Team {

    @Id
    @Column(name="team_id", updatable = false)
    private int teamId;
    @Column(name="team_name", nullable=false)
    private String teamName;
    @Column(name="stadium")
    private String stadium;
    @Column(name="team_logo")
    private String teamLogo;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="enrollments",
            joinColumns = @JoinColumn(name="team_id"),
            inverseJoinColumns = @JoinColumn(name="competition_id"))
    private List<Competition> competitions;

    @OneToMany(targetEntity = Person.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_fk", referencedColumnName = "team_id")
    private List<Person> personList;
}