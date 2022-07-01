package com.jacob.footballrater.mapper;

import com.jacob.footballrater.dtos.LeagueDto;
import com.jacob.footballrater.dtos.PlayerDto;
import com.jacob.footballrater.models.League;
import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.dtos.TeamDto;
import com.jacob.footballrater.models.Team;
import org.springframework.stereotype.Component;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public PlayerDto playerToPlayerDto(Player player) {
        if(player == null) return null;

        PlayerDto playerDto = PlayerDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .combinedName(player.getCombinedName())
                .teamName(player.getTeamName())
                .rating(player.getRating())
                .ratingList(player.getRatingList())
                .image(player.getImage())
                .league(player.getLeague())
                .build();

        return playerDto;
    }

    @Override
    public Player playerDtoToPlayer(PlayerDto playerDto) {
        if(playerDto == null) return null;

        Player player = Player.builder()
                .id(playerDto.getId())
                .firstName(playerDto.getFirstName())
                .lastName(playerDto.getLastName())
                .combinedName(playerDto.getCombinedName())
                .teamName(playerDto.getTeamName())
                .rating(playerDto.getRating())
                .ratingList(playerDto.getRatingList())
                .image(playerDto.getImage())
                .league(playerDto.getLeague())
                .build();

        return player;
    }

    @Override
    public TeamDto teamToTeamDto(Team team) {
        if(team == null) return null;

        TeamDto teamDto = TeamDto.builder()
                .id(team.getId())
                .teamName(team.getTeamName())
                .league(team.getLeague())
                .manager(team.getManager())
                .stadium(team.getStadium())
                .rating(team.getRating())
                .logo(team.getLogo())
                .build();

        return teamDto;
    }

    @Override
    public Team teamDtoToTeam(TeamDto teamDto) {
        if(teamDto == null) return null;

        Team team = Team.builder()
                .id(teamDto.getId())
                .teamName(teamDto.getTeamName())
                .league(teamDto.getLeague())
                .manager(teamDto.getManager())
                .stadium(teamDto.getStadium())
                .rating(teamDto.getRating())
                .logo(teamDto.getLogo())
                .build();

        return team;
    }

    @Override
    public LeagueDto leagueToLeagueDto(League league) {
        if(league == null) return null;

        LeagueDto leagueDto = LeagueDto.builder()
                .id(league.getId())
                .league(league.getLeague())
                .image(league.getImage())
                .build();

        return leagueDto;
    }

    @Override
    public League leagueDtoToLeague(LeagueDto leagueDto) {
        if(leagueDto == null) return null;

        League league = League.builder()
                .id(leagueDto.getId())
                .league(leagueDto.getLeague())
                .image(leagueDto.getImage())
                .build();

        return league;
    }


}
