package com.jacob.footballrater.services;

import com.jacob.footballrater.exceptions.ApiRequestException;
import com.jacob.footballrater.models.Person;
import com.jacob.footballrater.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Person getPerson(Integer id) {
        Person person = personRepository.findById(id).
                orElseThrow(() -> new ApiRequestException(String.format("Player could not be found with ID: %s", id)));

        return person;
    }

    public Person createPerson(Person person){
        Person p1 = personRepository.findById(person.getPersonId()).orElse(null);

        if(p1 != null)
            throw new ApiRequestException(String.format("A player already exists with that name: %s", person.getPersonId()));

        personRepository.save(person);
        return person;
    }

    public List<Person> createMultiplePersons(List<Person> personListRequest) {
        List<Person> personListResponse = new ArrayList<>();

        for(Person person: personListRequest) {
            Person p1 = personRepository.findById(person.getPersonId()).orElse(null);

            if(p1 != null)
                throw new ApiRequestException(String.format("A player already exists with that name: %s", person.getPersonId()));

            personListResponse.add(personRepository.save(person));
        }

        return personListResponse;
    }


}
