package com.jacob.footballrater.controllers;

import com.jacob.footballrater.dtos.CompetitionDto;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.mapper.MapStructMapperImpl;
import com.jacob.footballrater.models.Competition;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.services.CompetitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/competition")
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetition(@PathVariable("id") int id) {

        Competition competition = competitionService.getCompetition(id);
        CompetitionDto competitionResponse =  mapStructMapper.compeitionToCompetitionDto(competition);

        return new ResponseEntity<>(competitionResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDto>> getAllCompetitions() {

        List<Competition> competitionList = competitionService.getAllCompetitions();
        List<CompetitionDto> response = new ArrayList<>();
        for(Competition competition: competitionList) {
            response.add(mapStructMapper.compeitionToCompetitionDto(competition));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> createPerson(@RequestBody CompetitionDto competitionDto){

        Competition competition = mapStructMapper.compeitionDtoToCompetition(competitionDto);
        CompetitionDto competitionResponse =  mapStructMapper.compeitionToCompetitionDto(
                competitionService.createCompetition(competition));

        return new ResponseEntity<>(competitionResponse, HttpStatus.CREATED);
    }

    @PostMapping("all")
    public ResponseEntity<List<CompetitionDto>> createMultipleCompetition(@RequestBody List<CompetitionDto> competitionDtoList) {
        List<Competition> competitionList = new ArrayList<>();
        for(CompetitionDto c: competitionDtoList)
            competitionList.add(mapStructMapper.compeitionDtoToCompetition(c));

        List<Competition> competitionResponse = competitionService.createMultipleCompetitions(competitionList);
        List<CompetitionDto> competitionDtoResponse = new ArrayList<>();
        for(Competition c2: competitionResponse)
            competitionDtoResponse.add(mapStructMapper.compeitionToCompetitionDto(c2));

        return new ResponseEntity<>(competitionDtoResponse, HttpStatus.CREATED);
    }

}
