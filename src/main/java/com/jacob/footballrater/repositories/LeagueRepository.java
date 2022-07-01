package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeagueRepository extends JpaRepository<League, UUID> {
}
