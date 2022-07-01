package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    List<Team> findByTeamName(String teamName);

    List<Team> findByLeague(String league);

    List<Team> findAllByOrderByRatingDesc();
}
