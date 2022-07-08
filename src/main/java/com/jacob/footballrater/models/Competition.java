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
@Table(name="competition")
public class Competition {

    @Id
    @Column(name="competition_id", updatable = false)
    private int competitionId;
    @Column(name="competition_name")
    private String competitionName;
    @Column(name="competition_logo")
    private String competitionLogo;
    @Column(name="num_of_gameweeks")
    private int numOfGameweeks;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="enrollments",
            joinColumns = @JoinColumn(name="competition_id"),
            inverseJoinColumns = @JoinColumn(name="team_id"))
    private List<Team> teams;
}