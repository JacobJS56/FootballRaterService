package com.jacob.footballrater.repositories;

import com.jacob.footballrater.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByTeamId(int teamId);

}