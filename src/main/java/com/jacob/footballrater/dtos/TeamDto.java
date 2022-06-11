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
public class TeamDto {

    private UUID id;
    private String teamName;
    private String league;
    private Double rating;
    private String manager;
    private String stadium;

}