package com.javaDeveloperTasks.meetings.repositories;

import com.javaDeveloperTasks.meetings.entities.Meeting;
import com.javaDeveloperTasks.meetings.enums.MeetingCategory;
import com.javaDeveloperTasks.meetings.enums.MeetingType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    List<Meeting> findByDescription(String description);
    List<Meeting> findByResponsiblePerson(String responsiblePerson);
    List<Meeting> findByCategory(MeetingCategory category);
    List<Meeting> findByType(MeetingType type);
    List<Meeting> findByStartDate(LocalDate startDate);
}
