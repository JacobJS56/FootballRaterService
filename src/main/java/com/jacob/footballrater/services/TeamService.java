package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Team getTeam(Integer id) {
        Team team = teamRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Team could not be found with ID: %s", id)));

        return team;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team createTeam(Team team){
        Team t1 = teamRepository.findById(team.getTeamId()).orElse(null);

        if(t1 != null)
            throw new ApiRequestException(String.format("A team already exists with that name: %s", team.getTeamName()));

        return teamRepository.save(team);
    }

    public List<Team> createMultipleTeams(List<Team> teamListRequest) {
        List<Team> teamListResponse = new ArrayList<>();

        for(Team team: teamListRequest) {
            Team t1 = teamRepository.findById(team.getTeamId()).orElse(null);

            if(t1 != null)
                throw new ApiRequestException(String.format("A team already exists with that name: %s", team.getTeamName()));

            teamListResponse.add(teamRepository.save(team));
        }

        return teamListResponse;
    }
}
