package com.jacob.footballrater.controllers;

import com.jacob.footballrater.dtos.PersonDto;
import com.jacob.footballrater.dtos.RatingDto;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.models.Rating;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.services.PersonService;
import com.jacob.footballrater.mapper.MapStructMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v2/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final MapStructMapperImpl mapStructMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") int id) {

        Person player = personService.getPerson(id);
        PersonDto playerResponse =  mapStructMapper.personToPersonDto(player);

        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAllPersons() {

        List<Person> personList = personService.getAllPersons();
        List<PersonDto> response = new ArrayList<>();
        for(Person person: personList) {
            response.add(mapStructMapper.personToPersonDto(person));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all-team/{id}")
    public ResponseEntity<List<PersonDto>> getAllPersons(@PathVariable("id") int teamId) {

        List<Person> personList = personService.getAllPersonsByTeamId(teamId);
        List<PersonDto> response = new ArrayList<>();
        for(Person person: personList) {
            response.add(mapStructMapper.personToPersonDto(person));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){

        Person personRequest = mapStructMapper.personDtoToPerson(personDto);
        Person person = personService.createPerson(personRequest);
        PersonDto personResponse =  mapStructMapper.personToPersonDto(person);

        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<PersonDto>> createMultipleTeams(@RequestBody List<PersonDto> personDtoList) {
        List<Person> person = new ArrayList<>();
        for(PersonDto p: personDtoList)
            person.add(mapStructMapper.personDtoToPerson(p));

        List<Person> personResponse = personService.createMultiplePersons(person);
        List<PersonDto> personDtoResponse = new ArrayList<>();
        for(Person p2: personResponse)
            personDtoResponse.add(mapStructMapper.personToPersonDto(p2));

        return new ResponseEntity<>(personDtoResponse, HttpStatus.CREATED);
    }
    @PostMapping("/update-rating/{personId}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable("personId") int personId, @RequestBody RatingDto ratingDto) {
        Rating response = personService.updateRating(personId, ratingDto);
        return new ResponseEntity<>(mapStructMapper.ratingToRatingDto(response), HttpStatus.OK);
    }

}
