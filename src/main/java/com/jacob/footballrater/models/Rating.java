package com.jacob.footballrater.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="rating_id", updatable = false)
    private UUID ratingId;
    @Column(name="person_id", updatable = false)
    private int personId;
    @Column(name="competition_id")
    private int competitionId;
    @Column(name="season")
    private int	season;
    @Column(name="gameweek")
    private int gameweek;
    @Column(name="rating_total")
    private Double ratingTotal;
    @Column(name="number_of_ratings")
    private int numOfRatings;
}
