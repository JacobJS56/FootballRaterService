package com.jacob.footballrater.mapper;

import com.jacob.footballrater.dtos.LeagueDto;
import com.jacob.footballrater.dtos.PlayerDto;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.League;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import org.mapstruct.Mapper;

@Mapper
public interface MapStructMapper {

    PlayerDto playerToPlayerDto(Player player);
    Player playerDtoToPlayer(PlayerDto playerDto);

    TeamDto teamToTeamDto(Team team);
    Team teamDtoToTeam(TeamDto teamDto);

    LeagueDto leagueToLeagueDto(League league);
    League leagueDtoToLeague(LeagueDto leagueDto);
}
