package com.jacob.footballrater.controllers;

import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.services.TeamService;
import com.jacob.footballrater.mapper.MapStructMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") UUID id) {

        Team team = teamService.getTeam(id);
        TeamDto teamResponse =  mapStructMapper.teamToTeamDto(team);

        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @GetMapping("/all/{league}")
    public ResponseEntity<List<TeamDto>> getAllTeamsByLeague(@PathVariable("league") String league) {

        List<Team> teamList = teamService.getAllTeamsByLeague(league);
        List<TeamDto> response = new ArrayList<>();

        for(Team t: teamList)
            response.add(mapStructMapper.teamToTeamDto(t));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {

        Team teamRequest = mapStructMapper.teamDtoToTeam(teamDto);
        Team team = teamService.createTeam(teamRequest);
        TeamDto teamResponse =  mapStructMapper.teamToTeamDto(team);

        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
    }


    @GetMapping("/rating/{id}")
    public ResponseEntity<TeamDto> updateRating(@PathVariable("id") UUID id) {

        Team team = teamService.updateRating(id);
        TeamDto teamResponse =  mapStructMapper.teamToTeamDto(team);

        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
    }
}
