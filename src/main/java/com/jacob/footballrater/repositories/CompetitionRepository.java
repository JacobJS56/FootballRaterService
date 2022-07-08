package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.Competition;
import com.jacob.footballrater.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    List<Team> findAllByCompetitionId(int competitionId);
}