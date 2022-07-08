package com.jacob.footballrater.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue
    @Column(name="rating_id", updatable = false)
    private int ratingId;
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
