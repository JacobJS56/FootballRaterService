package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public Player getPlayer(UUID id){
        Player player = playerRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Player could not be found with ID: %s", id)));

        return player;
    }

    public List<Player> getAllPlayersByTeamName(String teamName) {
        List<Team> t1 = teamRepository.findByTeamName(teamName);

        if(t1 == null || t1.size() < 1)
            throw new ApiRequestException("Could not get players. Check team name exists");

        return playerRepository.getPlayerByTeamName(t1.get(0).getTeamName());
    }

    public Player createPlayer(Player player){
        List<Team> t1 = teamRepository.findByTeamName(player.getTeamName());

        if(t1 == null || t1.size() < 1)
            throw new ApiRequestException("Player could not be created. Check team name exists");

        playerRepository.save(player);
        return player;
    }

    public Player updateRating(UUID id, Player player) {
        if(player.getRating() > 10 || player.getRating() < 0)
            throw new ApiRequestException("InputtedRating cannot be less than 0 or more than 10");

        Player p1 = playerRepository.findById(id)
                .orElseThrow(() ->
                        new ApiRequestException(String.format("Player could not be found with ID: %s", id)));

        p1.getRatingList().add(player.getRating());

        double sum = 0;
        for(double i: p1.getRatingList())
            sum += i;
        sum = sum/p1.getRatingList().size();

        if(sum > 10 || sum < 0)
            throw new ApiRequestException("Ratting sum cannot be less than 0 or more than 10");

        p1.setRating(sum);

        playerRepository.save(p1);

        return p1;
    }

}
