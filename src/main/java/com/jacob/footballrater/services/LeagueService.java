package com.jacob.footballrater.services;

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

@Service
@AllArgsConstructor
public class LeagueService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;
    private final PlayerService playerService;

    public List<Team> createLeague(Map<String, Object> teamList) {
        List<Team> teamListResponse = new ArrayList<>();

        JSONObject obj = new JSONObject(teamList);

        for(Map.Entry<String, Object> entry: teamList.entrySet()) {
            JSONObject teamName = obj.getJSONObject(entry.getKey());
            JSONArray playerList = teamName.getJSONArray("playerList");

            Team team = Team.builder()
                    .teamName(entry.getKey())
                    .league(teamName.getString("league"))
                    .manager(teamName.getString("manager"))
                    .stadium(teamName.getString("stadium"))
                    .build();

            teamListResponse.add(teamService.createTeam(team));

            for(Object p: playerList) {
                String combinedName = p.toString();
                String[] nameSplit = combinedName.split(" ");
                String firstName = nameSplit[0];
                String lastName = "";

                if(nameSplit.length == 2) lastName = nameSplit[1];
                if(nameSplit.length == 3) lastName = nameSplit[1] + " " + nameSplit[2];

                Player player = Player.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .combinedName(combinedName)
                        .teamName(entry.getKey())
                        .build();

                playerService.createPlayer(player);
            }
        }

        return teamListResponse;
    }
}
