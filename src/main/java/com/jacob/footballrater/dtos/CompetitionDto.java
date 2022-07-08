package com.jacob.footballrater.dtos;

import com.jacob.footballrater.models.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDto {

    private int competitionId;
    private String competitionName;
    private String competitionLogo;
    private int numOfGameweeks;
    private List<TeamDto> teams;
}
