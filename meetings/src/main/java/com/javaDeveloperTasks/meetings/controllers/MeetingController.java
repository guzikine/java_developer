package com.javaDeveloperTasks.meetings.controllers;

import com.javaDeveloperTasks.meetings.entities.AddRemovePerson;
import com.javaDeveloperTasks.meetings.entities.Meeting;
import com.javaDeveloperTasks.meetings.entities.Person;
import com.javaDeveloperTasks.meetings.enums.MeetingCategory;
import com.javaDeveloperTasks.meetings.enums.MeetingType;
import com.javaDeveloperTasks.meetings.repositories.MeetingRepository;
import com.javaDeveloperTasks.meetings.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/meetings")
public class MeetingController {
    private MeetingRepository meetingRepository;
    private PersonRepository personRepository;

    public MeetingController(final MeetingRepository meetingRepository, final PersonRepository personRepository) {
        this.meetingRepository = meetingRepository;
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Meeting> getAllMeetings() {
        return this.meetingRepository.findAll();
    }

    @PostMapping
    public Meeting createNewMeeting(@RequestBody Meeting meeting) {
        Meeting newMeeting = this.meetingRepository.save(meeting);
        return newMeeting;
    }

    @DeleteMapping("{id}/{personId}")
    public Meeting deleteMeeting(@PathVariable("id") Long id, @PathVariable("personId") Long personId) {
        Optional<Meeting> meetingToDeleteOptional = this.meetingRepository.findById(id);
        Optional<Person> personTryingToDeleteOptional = this.personRepository.findById(personId);

        if (!meetingToDeleteOptional.isPresent()) {
            throw new IllegalStateException("Meeting with id:{" + personId + "} does not exist.");
        }
        else if (!personTryingToDeleteOptional.isPresent()) {
            throw new IllegalStateException("Person with id:{" + id + "} does not exist.");
        }
        Meeting meetingToDelete = meetingToDeleteOptional.get();
        Person personTryingToDelete = personTryingToDeleteOptional.get();

        if (!personTryingToDelete.getName().equals(meetingToDelete.getResponsiblePerson())) {
            throw new IllegalStateException("Person who is not responsible for the meeting cannot delete the meeting.");
        }

        this.meetingRepository.delete(meetingToDelete);
        return meetingToDelete;
    }

    @PutMapping("{id}/addPerson")
    public Meeting addPerson(@PathVariable("id") Long id, @RequestBody AddRemovePerson person) {
        Optional<Meeting> meetingToUpdateOptional = this.meetingRepository.findById(id);

        if(!meetingToUpdateOptional.isPresent()) {
            throw new IllegalStateException("Meeting with id:{" + id + "} does not exist.");
        }

        Meeting meetingToUpdate = meetingToUpdateOptional.get();
        for(String name: meetingToUpdate.getAttendees()) {
            if(name.equals(person.getName())) {
                throw new IllegalStateException("Specified person will already attend this meeting.");
            }
        }

        List<String> newAttendeeList = meetingToUpdate.getAttendees();
        newAttendeeList.add(person.getName());
        meetingToUpdate.setAttendees(newAttendeeList);
        return meetingToUpdate;
    }

    @PutMapping("{id}/removePerson")
    public Meeting removePerson(@PathVariable("id") Long id, @RequestBody AddRemovePerson person) {
        Optional<Meeting> meetingToUpdateOptional = this.meetingRepository.findById(id);

        if(!meetingToUpdateOptional.isPresent()) {
            throw new IllegalStateException("Meeting with id:{" + id + "} does not exist.");
        }

        Meeting meetingToUpdate = meetingToUpdateOptional.get();

        if(person.getName().equals(meetingToUpdate.getResponsiblePerson())) {
            throw new IllegalStateException("Cannot remove the person who is responsible for the meeting.");
        }

        List<String> newAttendeeList = meetingToUpdate.getAttendees();

        if(!newAttendeeList.contains(person.getName())) {
            throw new IllegalStateException("Cannot remove the person, because no such person exists.");
        }

        newAttendeeList.remove(String.valueOf(person.getName()));
        meetingToUpdate.setAttendees(newAttendeeList);
        return meetingToUpdate;
    }

    @GetMapping("search")
    public List<Meeting> searchMeetings(
            @RequestParam(name="description", required = false) String description,
            @RequestParam(name="responsiblePerson", required = false) String responsiblePerson,
            @RequestParam(name="category", required = false) MeetingCategory category,
            @RequestParam(name="type", required = false) MeetingType type,
            @RequestParam(name="date1", required = false) LocalDate date1,
            @RequestParam(name="date2", required = false) LocalDate date2,
            @RequestParam(name="attendeeQuantity", required = false) Integer attendeeQuantity) {

            if(description != null) {
                return this.meetingRepository.findByDescription(description);
            }

            if(responsiblePerson != null) {
                return this.meetingRepository.findByResponsiblePerson(responsiblePerson);
            }

            if(category != null) {
                return this.meetingRepository.findByCategory(category);
            }

            if(type != null) {
                return this.meetingRepository.findByType(type);
            }

            if(date1 != null && date2 == null) {
                return this.meetingRepository.findByStartDate(date1);
            }

            return new ArrayList<>();
    }

}
