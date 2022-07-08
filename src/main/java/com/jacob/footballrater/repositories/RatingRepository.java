package com.jacob.footballrater.repositories;
import com.jacob.footballrater.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value="SELECT * FROM rating r " +
            "WHERE r.person_id = :person_id " +
            "AND r.competition_id = :competition_id " +
            "AND r.season = :season " +
            "AND r.gameweek = :gameweek ", nativeQuery = true)
    Rating findRatingByCompetitionIdAndAndSeasonAndGameweek(
            @Param("person_id") int personId,
            @Param("competition_id") int competitionId,
            @Param("season") int season,
            @Param("gameweek") int gameweek);
}