package com.jacob.footballrater.mapper;

import com.jacob.footballrater.dtos.PlayerDto;
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
                .build();

        return team;
    }


}
