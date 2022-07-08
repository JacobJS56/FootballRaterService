package com.jacob.footballrater.dtos;

import com.jacob.footballrater.models.Competition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private int teamId;
    private String teamName;
    private String stadium;
    private String teamLogo;
}