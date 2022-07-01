package com.jacob.footballrater.controllers;

import com.jacob.footballrater.dtos.LeagueDto;
import com.jacob.footballrater.dtos.PlayerDto;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.mapper.MapStructMapperImpl;
import com.jacob.footballrater.models.League;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.services.LeagueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/league")
@AllArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/all")
    public ResponseEntity<List<LeagueDto>> getAllLeagues() {

        List<League> leagueList = leagueService.getAllLeagues();
        List<LeagueDto> response = new ArrayList<>();

        for(League l: leagueList)
            response.add(mapStructMapper.leagueToLeagueDto(l));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<TeamDto>> createLeague(@RequestBody Map<String, Object> teamList) {

        List<Team> teamResponse = leagueService.createLeague(teamList);

        List<TeamDto> response = new ArrayList<>();
        for(Team t: teamResponse)
            response.add(mapStructMapper.teamToTeamDto(t));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
