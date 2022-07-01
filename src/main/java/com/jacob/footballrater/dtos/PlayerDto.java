package com.jacob.footballrater.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto  {

    private UUID id;
    private String firstName;
    private String lastName;
    private String combinedName;
    private String teamName;
    private Double rating;
    private List<Double> ratingList;
    private String image;
    private String league;
}
