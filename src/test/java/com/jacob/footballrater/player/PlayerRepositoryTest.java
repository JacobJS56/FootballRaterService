package com.jacob.footballrater.player;

import com.jacob.footballrater.models.Player;
import com.jacob.footballrater.models.Team;
import com.jacob.footballrater.repositories.PlayerRepository;
import com.jacob.footballrater.repositories.TeamRepository;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository underTest;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        playerOne = Player.builder()
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
        playerTwo = Player.builder()
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
        underTest.save(playerOne);
        underTest.save(playerTwo);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldGetPlayerByTeamNameOrderByRatingDesc() {
        String teamName = "Manchester United";
        Player actual = underTest.getPlayerByTeamNameOrderByRatingDesc(teamName).get(0);
        assertThat(playerOne).isEqualTo(actual);
    }

    @Test
    void itShouldFindAllByOrderByRatingDesc() {
        List<Player> expected = new ArrayList<>();
        expected.add(playerOne);
        expected.add(playerTwo);

        List<Player> actual = underTest.findAllByOrderByRatingDesc();
        assertThat(expected).isEqualTo(actual);
    }
}