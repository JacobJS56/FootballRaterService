/*
package com.jacob.footballrater.repository;

import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.repositories.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository underTest;
    private Player rashford;
    private Player kroos;

    @BeforeEach
    void setup() {
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
        kroos = Player.builder()
                .firstName("Toni")
                .lastName("Kroos")
                .combinedName("Toni Kroos")
                .teamName("Real Madrid")
                .rating(4.2)
                .ratingTotal(12.6)
                .numOfRatings(3)
                .image("image")
                .league("La Liga Santander")
                .build();
        underTest.save(rashford);
        underTest.save(kroos);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldGetPlayerByTeamNameOrderByRatingDesc() {
        // given
        String teamName = "Manchester United";
        // when
        Player actual = underTest.getPlayerByTeamNameOrderByRatingDesc(teamName).get(0);
        // then
        assertThat(rashford).isEqualTo(actual);
    }

    @Test
    void itShouldFindAllByOrderByRatingDesc() {
        // given
        List<Player> expected = new ArrayList<>();
        expected.add(rashford);
        expected.add(kroos);
        // when
        List<Player> actual = underTest.findAllByOrderByRatingDesc();
        // then
        assertThat(expected).isEqualTo(actual);
    }
}*/
