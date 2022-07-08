package com.jacob.footballrater.mapper;

import com.jacob.footballrater.dtos.CompetitionDto;
import com.jacob.footballrater.dtos.PersonDto;
import com.jacob.footballrater.dtos.RatingDto;
import com.jacob.footballrater.models.Competition;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.Rating;
import com.jacob.footballrater.models.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public PersonDto personToPersonDto(Person person) {
        if(person == null) return null;

        PersonDto personDto = PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .teamId(person.getTeamId())
                .overallRating(person.getOverallRating())
                .ratingList(person.getRatingList())
                .position(person.getPosition())
                .shirtNumber(person.getShirtNumber())
                .personImage(person.getPersonImage())
                .build();

        return personDto;
    }

    @Override
    public Person personDtoToPerson(PersonDto personDto) {
        if(personDto == null) return null;

        Person person = Person.builder()
                .personId(personDto.getPersonId())
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .teamId(personDto.getTeamId())
                .overallRating(personDto.getOverallRating())
                .ratingList(personDto.getRatingList())
                .position(personDto.getPosition())
                .shirtNumber(personDto.getShirtNumber())
                .personImage(personDto.getPersonImage())
                .build();

        return person;
    }

    @Override
    public TeamDto teamToTeamDto(Team team) {
        if(team == null) return null;

        TeamDto teamDto = TeamDto.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .stadium(team.getStadium())
                .teamLogo(team.getTeamLogo())
                .build();

        return teamDto;
    }

    @Override
    public Team teamDtoToTeam(TeamDto teamDto) {
        if(teamDto == null) return null;

        Team team = Team.builder()
                .teamId(teamDto.getTeamId())
                .teamName(teamDto.getTeamName())
                .stadium(teamDto.getStadium())
                .teamLogo(teamDto.getTeamLogo())
                .build();

        return team;
    }

    @Override
    public CompetitionDto compeitionToCompetitionDto(Competition competition) {
        if(competition == null) return null;
        List<TeamDto> teamDtoList = new ArrayList<>();
        for(Team t: competition.getTeams())
            teamDtoList.add(teamToTeamDto(t));

        CompetitionDto competitionDto = CompetitionDto.builder()
                .competitionId(competition.getCompetitionId())
                .competitionName(competition.getCompetitionName())
                .competitionLogo(competition.getCompetitionLogo())
                .numOfGameweeks(competition.getNumOfGameweeks())
                .teams(teamDtoList)
                .build();

        return competitionDto;
    }

    @Override
    public Competition compeitionDtoToCompetition(CompetitionDto competitionDto) {
        if(competitionDto == null) return null;
        List<Team> teamList = new ArrayList<>();
        for(TeamDto t: competitionDto.getTeams())
            teamList.add(teamDtoToTeam(t));

        Competition competition = Competition.builder()
                .competitionId(competitionDto.getCompetitionId())
                .competitionName(competitionDto.getCompetitionName())
                .competitionLogo(competitionDto.getCompetitionLogo())
                .numOfGameweeks(competitionDto.getNumOfGameweeks())
                .teams(teamList)
                .build();

        return competition;
    }

    @Override
    public Rating ratingDtoToRating(RatingDto ratingDto) {
        if(ratingDto == null) return null;

        Rating rating = Rating.builder()
                .ratingId(ratingDto.getRatingId())
                .personId(ratingDto.getPersonId())
                .competitionId(ratingDto.getCompetitionId())
                .season(ratingDto.getSeason())
                .gameweek(ratingDto.getGameweek())
                .ratingTotal(ratingDto.getRatingTotal())
                .numOfRatings(ratingDto.getNumOfRatings())
                .build();

        return rating;
    }

    @Override
    public RatingDto ratingToRatingDto(Rating rating) {
        if(rating == null) return null;

        RatingDto ratingDto = RatingDto.builder()
                .ratingId(rating.getRatingId())
                .personId(rating.getPersonId())
                .competitionId(rating.getCompetitionId())
                .season(rating.getSeason())
                .gameweek(rating.getGameweek())
                .ratingTotal(rating.getRatingTotal())
                .numOfRatings(rating.getNumOfRatings())
                .build();

        return ratingDto;
    }


}
