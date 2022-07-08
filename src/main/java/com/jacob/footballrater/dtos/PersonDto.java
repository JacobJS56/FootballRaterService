package com.jacob.footballrater.dtos;

import com.jacob.footballrater.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private int personId;
    private String firstName;
    private String lastName;
    private int teamId;
    private Double overallRating;
    private String position;
    private int shirtNumber;
    private String personImage;
    private List<Rating> ratingList;
}
