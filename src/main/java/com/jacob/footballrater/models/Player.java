package com.jacob.footballrater.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="id", updatable = false)
    private UUID id;
    @Column(name="first_name", nullable=false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name="last_name", nullable=false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name="combined_name", nullable=false, columnDefinition = "TEXT")
    private String combinedName;
    @Column(name="team_name", nullable=false, columnDefinition = "TEXT")
    private String teamName;
    @Value("${some.key:0}")
    @Column(name="rating")
    private Double rating;
    @Column(name="rating_total")
    private Double ratingTotal;
    @Column(name="number_of_ratings")
    private int numOfRatings;
    @Column(name="image")
    private String image;
    @Column(name="league")
    private String league;

}
