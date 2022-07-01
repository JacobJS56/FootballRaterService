package com.jacob.footballrater.services;

import com.jacob.footballrater.models.League;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.LeagueRepository;
import com.jacob.footballrater.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
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
    private final TeamRepository teamRepository;

    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public List<Team> createLeague(Map<String, Object> teamList) {
        List<Team> teamListResponse = new ArrayList<>();
        JSONObject obj = new JSONObject(teamList);
        JSONArray teamJSONArray = obj.getJSONArray("teamList");

        System.out.println(obj.getString("league"));
        System.out.println(obj.getString("image"));

        leagueRepository.save(League.builder()
                .league(obj.getString("league"))
                .image(obj.getString("image"))
                .build());

        for(Object t: teamJSONArray) {
            JSONObject tJSONObj = (JSONObject) t;
            JSONArray playerList = tJSONObj.getJSONArray("playerList");

            Team team = Team.builder()
                    .teamName(tJSONObj.getString("teamName"))
                    .league(obj.getString("league"))
                    .manager(tJSONObj.getString("manager"))
                    .stadium(tJSONObj.getString("stadium"))
                    .logo(tJSONObj.getString("logo"))
                    .build();
            teamListResponse.add(teamService.createTeam(team));

            Random random = new Random();
            for(Object p: playerList) {
                JSONObject jsonObj = (JSONObject) p;
                String combinedName = jsonObj.getString("name");

                String[] nameSplit = combinedName.split(" ");
                String firstName = nameSplit[0];
                String lastName = "";

                if(nameSplit.length == 2) lastName = nameSplit[1];
                if(nameSplit.length == 3) lastName = nameSplit[1] + " " + nameSplit[2];

                // quick modification instead of rating for testing
                double nthRandomNumber = random.nextInt(1000) / 100.0;

                Player player = Player.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .combinedName(combinedName)
                        .image(jsonObj.getString("image"))
                        .teamName(tJSONObj.getString("teamName"))
                        .rating(nthRandomNumber)
                        .league(obj.getString("league"))
                        .build();

                playerService.createPlayer(player);
            }
        }
        return teamListResponse;
    }

}
