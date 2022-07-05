package com.jacob.footballrater.team;

import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamRepositoryTest {

    @Autowired
    private TeamRepository underTest;
    private Team teamOne;
    private Team teamTwo;

    @BeforeEach
    void setUp() {
        teamOne = Team.builder()
                .teamName("Manchester United")
                .league("Premier League")
                .manager("Ralf Rangnick")
                .stadium("Old Trafford")
                .rating(7.8)
                .logo("www.googledrive.com/logo")
                .build();
        teamTwo = Team.builder()
                .teamName("Real Madrid")
                .league("La liga")
                .manager("Carlo Ancelotti")
                .stadium("Santiago Bernabéu Stadium")
                .rating(5.1)
                .logo("www.googledrive.com/logo")
                .build();
        underTest.save(teamOne);
        underTest.save(teamTwo);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByTeamName() {
        String teamName = "Manchester United";
        Team actual = underTest.findByTeamName(teamName).get(0);
        assertThat(teamOne).isEqualTo(actual);
    }

    @Test
    void itShouldFindByLeague() {
        String league = "Premier League";
        Team actual = underTest.findByLeague(league).get(0);
        assertThat(teamOne).isEqualTo(actual);
    }

    @Test
    void itShouldFindAllByOrderByRatingDesc() {
        List<Team> expected = new ArrayList<>();
        expected.add(teamOne);
        expected.add(teamTwo);
        List<Team> actual = underTest.findAllByOrderByRatingDesc();
        assertThat(expected).isEqualTo(actual);
    }
}