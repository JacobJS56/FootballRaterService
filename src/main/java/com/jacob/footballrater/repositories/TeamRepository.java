package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Person> findAllByTeamId(int teamId);

}
