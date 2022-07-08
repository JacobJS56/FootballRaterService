package com.jacob.footballrater.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue
    @Column(name="person_id", updatable = false)
    private int personId;
    @Column(name="first_name", nullable=false)
    private String firstName;
    @Column(name="last_name", nullable=false)
    private String lastName;
    @Column(name="team_id", nullable=false)
    private int teamId;
    @Value("${some.key:0}")
    @Column(name="overall_rating")
    private Double overallRating;
    @Column(name="position")
    private String position;
    @Column(name="shirt_number")
    private int shirtNumber;
    @Column(name="person_image")
    private String personImage;

    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private List<Rating> ratingList;
}
