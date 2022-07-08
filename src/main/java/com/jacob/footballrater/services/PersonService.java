package com.jacob.footballrater.services;

import com.jacob.footballrater.dtos.RatingDto;
import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.models.Rating;
import com.jacob.footballrater.repositories.PersonRepository;
import com.jacob.footballrater.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RatingRepository ratingRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Person getPerson(Integer id) {
        Person person = personRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Person could not be found with ID: %s", id)));
        return person;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getAllPersonsByTeamId(int teamId) {
        return personRepository.findAllByTeamId(teamId);
    }
    public Person createPerson(Person person){
        Person p1 = personRepository.findById(person.getPersonId()).orElse(null);

        if(p1 != null)
            throw new ApiRequestException(String.format("A person already exists: %s", person.getPersonId()));

        personRepository.save(person);
        return person;
    }

    public List<Person> createMultiplePersons(List<Person> personListRequest) {
        List<Person> personListResponse = new ArrayList<>();

        for(Person person: personListRequest) {
            Person p1 = personRepository.findById(person.getPersonId()).orElse(null);

            if(p1 != null)
                throw new ApiRequestException(String.format("A person already exists: %s", person.getPersonId()));

            personListResponse.add(personRepository.save(person));
        }

        return personListResponse;
    }

    public Rating updateRating(int personId, RatingDto ratingDto) {
        // check person exists
        System.out.println(personId);
        Person p1 = personRepository.findById(personId).orElse(null);
        System.out.println(p1);
        if(p1 == null)
            throw new ApiRequestException(String.format("That person does not exist: %s", personId));

        // check rating exists
        Rating rating = ratingRepository.findRatingByCompetitionIdAndAndSeasonAndGameweek(
                personId,
                ratingDto.getCompetitionId(),
                ratingDto.getSeason(),
                ratingDto.getGameweek()
        );

        // if does not exist
        if(rating == null) {
            rating = Rating.builder()
                    .personId(personId)
                    .competitionId(ratingDto.getCompetitionId())
                    .season(ratingDto.getSeason())
                    .gameweek(ratingDto.getGameweek())
                    .numOfRatings(0)
                    .ratingTotal(0.00)
                    .build();
        }

        rating.setNumOfRatings(rating.getNumOfRatings() + 1);
        rating.setRatingTotal(rating.getRatingTotal() + ratingDto.getUserRating());
        ratingRepository.save(rating);

        p1.getRatingList().add(rating);
        return rating;
    }

}
