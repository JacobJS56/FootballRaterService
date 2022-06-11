package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public Team getTeam(UUID id){
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(String.format("Team could not find a team with ID: %s", id)));

        return team;
    }

    public List<Team> getAllTeamsByLeague(String league) {
        return teamRepository.findByLeague(league);
    }

    public Team createTeam(Team team){
        List<Team> t1 = teamRepository.findByTeamName(team.getTeamName());

        if(t1.size() > 0)
            throw new ApiRequestException(String.format("A team already exists with that name: %s", team.getTeamName()));

        teamRepository.save(team);
        return team;
    }

    public Team updateRating(UUID id) {
        Team t1 = teamRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(String.format("Team could not find a team with ID: %s", id)));

        List<Player> listOfPlayers = playerRepository.getPlayerByTeamName(t1.getTeamName());

        double sum = 0;
        for(Player p: listOfPlayers)
            sum += p.getRating();

        sum = sum/listOfPlayers.size();

        if(sum > 10 || sum < 0)
            throw new ApiRequestException("Ratting sum cannot be less than 0 or more than 10");

        t1.setRating(sum);
        teamRepository.save(t1);

        return t1;
    }

}
