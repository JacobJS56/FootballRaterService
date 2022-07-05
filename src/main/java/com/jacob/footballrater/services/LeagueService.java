package com.jacob.footballrater.services;

import com.jacob.footballrater.models.League;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.LeagueRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@AllArgsConstructor
public class LeagueService {
    private final TeamService teamService;
    private final PlayerService playerService;
    private final LeagueRepository leagueRepository;

    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public List<Team> createLeague(Map<String, Object> teamList) {
        List<Team> teamListResponse = new ArrayList<>();
        JSONObject leagueObj = new JSONObject(teamList);
        JSONArray teamJSONArray = leagueObj.getJSONArray("teamList");

        leagueRepository.save(League.builder()
                .league(leagueObj.getString("league"))
                .image(leagueObj.getString("image"))
                .build());

        for(Object t: teamJSONArray) {
            JSONObject teamJSONObj = (JSONObject) t;
            JSONArray playerList = teamJSONObj.getJSONArray("playerList");

            Team team = Team.builder()
                    .teamName(teamJSONObj.getString("teamName"))
                    .league(leagueObj.getString("league"))
                    .manager(teamJSONObj.getString("manager"))
                    .stadium(teamJSONObj.getString("stadium"))
                    .logo(teamJSONObj.getString("logo"))
                    .build();
            teamListResponse.add(teamService.createTeam(team));

            Random random = new Random();
            for(Object p: playerList) {
                JSONObject playerJSONObj = (JSONObject) p;
                
                // name code
                String combinedName = playerJSONObj.getString("name");
                String[] nameSplit = combinedName.split(" ");
                String firstName = nameSplit[0];
                String lastName = "";
                if(nameSplit.length == 2) lastName = nameSplit[1];
                if(nameSplit.length == 3) lastName = nameSplit[1] + " " + nameSplit[2];

                // quick modification instead of rating for testing
                double randomRating = random.nextInt(1000) / 100.0;

                Player player = Player.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .combinedName(combinedName)
                        .image(playerJSONObj.getString("image"))
                        .teamName(teamJSONObj.getString("teamName"))
                        .rating(randomRating)
                        .ratingTotal(randomRating)
                        .numOfRatings(1)
                        .league(leagueObj.getString("league"))
                        .build();

                playerService.createPlayer(player);
            }
            teamService.updateRating(team.getId());
        }
        return teamListResponse;
    }

}
