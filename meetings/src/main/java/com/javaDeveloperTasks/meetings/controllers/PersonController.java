package com.javaDeveloperTasks.meetings.controllers;

import com.javaDeveloperTasks.meetings.entities.Person;
import com.javaDeveloperTasks.meetings.repositories.MeetingRepository;
import com.javaDeveloperTasks.meetings.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/people")
public class PersonController {
    private PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> getAllPeople() {
        return this.personRepository.findAll();
    }

    @PostMapping
    public Person createNewPerson(@RequestBody Person person) {
        Person newPerson = this.personRepository.save(person);
        return newPerson;
    }
}
