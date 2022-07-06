package com.jacob.footballrater.services;

import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private TeamService teamService;

    private Player rashford;
    private Team manchesterUtd;
    private Team realMadrid;

    @BeforeEach
    void setup() {
        // mocks
        teamRepository = Mockito.mock(TeamRepository.class);
        playerRepository = Mockito.mock(PlayerRepository.class);
        teamService = new TeamService(teamRepository, playerRepository);

        // given
        rashford = Player.builder()
                .firstName("Marcus")
                .lastName("Rashford")
                .combinedName("Marcus Rashford")
                .teamName("Manchester United")
                .rating(8.3)
                .ratingTotal(8.3)
                .numOfRatings(1)
                .image("image")
                .league("Premier League")
                .build();

        manchesterUtd = Team.builder()
                .id(UUID.randomUUID())
                .teamName("Manchester United")
                .league("Premier League")
                .manager("Ralf Rangnick")
                .stadium("Old Trafford")
                .rating(7.8)
                .logo("www.googledrive.com/logo")
                .build();

        realMadrid = Team.builder()
                .id(UUID.randomUUID())
                .teamName("Real Madrid")
                .league("La liga")
                .manager("Carlo Ancelotti")
                .stadium("Santiago Bernabéu Stadium")
                .rating(5.1)
                .logo("www.googledrive.com/logo")
                .build();
    }

    @Test
    void createTeam() {
        // given
        List<Team> manchesterList = new ArrayList<>();
        manchesterList.add(manchesterUtd);
        BDDMockito.given(teamRepository.findByTeamName(manchesterUtd.getTeamName()))
                .willReturn(manchesterList);

        // when
        Team actual = teamService.createTeam(manchesterUtd);

        // then
        assertThat(actual).isEqualTo(manchesterUtd);
    }

    @Test
    void updateRating() {
        // given
        List<Player> playerList = new ArrayList<>();
        playerList.add(rashford);

        BDDMockito.given(teamRepository.findById(manchesterUtd.getId()))
                .willReturn(Optional.ofNullable(manchesterUtd));
        BDDMockito.given(playerRepository.getPlayerByTeamNameOrderByRatingDesc(manchesterUtd.getTeamName()))
                .willReturn(playerList);

        // when
        Team actual = teamService.updateRating(manchesterUtd.getId());

        // then
        assertThat(actual).isEqualTo(manchesterUtd);
    }
}