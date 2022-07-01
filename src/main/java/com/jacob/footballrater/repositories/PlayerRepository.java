package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    List<Player> getPlayerByTeamNameOrderByRatingDesc(String teamName);

}
