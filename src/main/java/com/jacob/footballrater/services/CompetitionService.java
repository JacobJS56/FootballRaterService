package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Competition;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionService {

    private CompetitionRepository competitionRepository;

    public Competition getCompetition(int id) {
        Competition competition = competitionRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Competition could not be found with ID: %s", id)));

        return competition;
    }

    public Competition createCompetition(Competition competition) {
        Competition c1 = competitionRepository.findById(competition.getCompetitionId()).orElse(null);

        if(c1 != null)
            throw new ApiRequestException(String.format("A team already exists with that name: %s", competition.getCompetitionId()));

        return competitionRepository.save(competition);
    }

    public List<Competition> createMultipleCompetitions(List<Competition> competitionList) {
        List<Competition> competitionListResponse = new ArrayList<>();

        for(Competition competition: competitionList) {
            Competition c1 = competitionRepository.findById(competition.getCompetitionId()).orElse(null);

            if(c1 != null)
                throw new ApiRequestException(String.format("A team already exists with that name: %s", competition.getCompetitionId()));

            competitionListResponse.add(competitionRepository.save(competition));
        }

        return competitionListResponse;
    }
}
