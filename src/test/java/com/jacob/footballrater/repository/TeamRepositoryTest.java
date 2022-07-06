/*package com.jacob.footballrater.repository;

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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository underTest;
    private Team manchesterUtd;
    private Team realMadrid;

    @BeforeEach
    void setup() {
        manchesterUtd = Team.builder()
                .teamName("Manchester United")
                .league("Premier League")
                .manager("Ralf Rangnick")
                .stadium("Old Trafford")
                .rating(7.8)
                .logo("www.googledrive.com/logo")
                .build();
        realMadrid = Team.builder()
                .teamName("Real Madrid")
                .league("La liga")
                .manager("Carlo Ancelotti")
                .stadium("Santiago Bernabéu Stadium")
                .rating(5.1)
                .logo("www.googledrive.com/logo")
                .build();
        underTest.save(manchesterUtd);
        underTest.save(realMadrid);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByTeamName() {
        // given
        String teamName = "Manchester United";
        // when
        Team actual = underTest.findByTeamName(teamName).get(0);
        // then
        assertThat(manchesterUtd).isEqualTo(actual);
    }


    @Test
    void itShouldFindByLeague() {
        // given
        String league = "Premier League";
        // when
        Team actual = underTest.findByLeague(league).get(0);
        // then
        assertThat(manchesterUtd).isEqualTo(actual);
    }

    @Test
    void itShouldFindAllByOrderByRatingDesc() {
        // given
        List<Team> expected = new ArrayList<>();
        expected.add(manchesterUtd);
        expected.add(realMadrid);
        // when
        List<Team> actual = underTest.findAllByOrderByRatingDesc();
        // then
        assertThat(expected).isEqualTo(actual);
    }
}*/
