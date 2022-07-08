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

@CrossOrigin
@RestController
@RequestMapping("/api/v2/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") int id) {

        Team team = teamService.getTeam(id);
        TeamDto teamResponse =  mapStructMapper.teamToTeamDto(team);

        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeamDto>> getAllTeams() {

        List<Team> teamList = teamService.getAllTeams();
        List<TeamDto> response = new ArrayList<>();
        for(Team team: teamList) {
            response.add(mapStructMapper.teamToTeamDto(team));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {

        Team team = mapStructMapper.teamDtoToTeam(teamDto);
        TeamDto teamResponse =  mapStructMapper.teamToTeamDto(
                teamService.createTeam(team));

        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
    }

    @PostMapping("all")
    public ResponseEntity<List<TeamDto>> createMultipleTeams(@RequestBody List<TeamDto> teamDtoList) {
        List<Team> team = new ArrayList<>();
        for(TeamDto t: teamDtoList)
            team.add(mapStructMapper.teamDtoToTeam(t));

        List<Team> teamResponse = teamService.createMultipleTeams(team);
        List<TeamDto> teamDtoResponse = new ArrayList<>();
        for(Team t: teamResponse)
            teamDtoResponse.add(mapStructMapper.teamToTeamDto(t));

        return new ResponseEntity<>(teamDtoResponse, HttpStatus.CREATED);
    }

}
