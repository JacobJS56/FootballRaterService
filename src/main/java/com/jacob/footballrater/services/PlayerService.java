package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Player getPlayer(UUID id){
        Player player = playerRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Player could not be found with ID: %s", id)));

        return player;
    }

    public List<Player> getAllPlayersByTeamName(String teamName) {
        List<Team> t1 = teamRepository.findByTeamName(teamName);

        if(t1 == null || t1.size() < 1)
            throw new ApiRequestException("Could not get players. Check team name exists");

        return playerRepository.getPlayerByTeamNameOrderByRatingDesc(t1.get(0).getTeamName());
    }

    public List<Player> getTop20Players() {
        List<Player> playerList = playerRepository.findAllByOrderByRatingDesc();

        return playerList.subList(0, 19);
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

        double ratingTotal = p1.getRatingTotal() + player.getRating();
        int numOfRatings = p1.getNumOfRatings() + 1;
        double sum = ratingTotal / numOfRatings;

        if(sum > 10 || sum < 0)
            throw new ApiRequestException("Ratting sum cannot be less than 0 or more than 10");

        p1.setRating(Double.parseDouble(df.format(sum)));
        p1.setRatingTotal(ratingTotal);
        p1.setNumOfRatings(numOfRatings);

        playerRepository.save(p1);

        return p1;
    }

}
