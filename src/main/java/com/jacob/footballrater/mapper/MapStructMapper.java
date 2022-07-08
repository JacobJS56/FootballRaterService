package com.jacob.footballrater.mapper;

import com.jacob.footballrater.dtos.CompetitionDto;
import com.jacob.footballrater.dtos.PersonDto;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.Competition;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.models.Team;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructMapper {

    PersonDto personToPersonDto(Person player);
    Person personDtoToPerson(PersonDto personDto);

    TeamDto teamToTeamDto(Team team);
    Team teamDtoToTeam(TeamDto teamDto);

    CompetitionDto compeitionToCompetitionDto(Competition competition);
    Competition compeitionDtoToCompetition(CompetitionDto competitionDto);

}
