package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Team getTeam(UUID id){
        Team t1 = updateRating(id);

        return t1;
    }

    public List<Team> getAllTeamsByLeague(String league) {
        List<Team> listOfTeams = teamRepository.findByLeague(league);
        List<Team> newList = new ArrayList<>();

        for(Team t: listOfTeams) {
            newList.add(updateRating(t.getId()));
        }

        return listOfTeams;
    }

    public List<Team> getTop20Teams() {
        List<Team> teamList = teamRepository.findAll(Sort.by(Sort.Direction.DESC, "Rating"));

        return teamList.subList(0, 20);
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

        List<Player> listOfPlayers = playerRepository.getPlayerByTeamNameOrderByRatingDesc(t1.getTeamName());

        double sum = 0;
        for(Player p: listOfPlayers)
            sum += p.getRating();

        sum = sum/listOfPlayers.size();

        if(sum > 10 || sum < 0)
            throw new ApiRequestException("Ratting sum cannot be less than 0 or more than 10");

        t1.setRating(Double.parseDouble(df.format(sum)));
        teamRepository.save(t1);

        return t1;
    }

}
