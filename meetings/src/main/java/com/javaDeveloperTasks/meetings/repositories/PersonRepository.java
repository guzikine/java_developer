package com.javaDeveloperTasks.meetings.repositories;

import com.javaDeveloperTasks.meetings.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
