package com.jacob.footballrater.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private UUID ratingId;
    private int personId;
    private int competitionId;
    private int	season;
    private int gameweek;
    private Double ratingTotal;
    private int numOfRatings;
    private Double userRating;

}
