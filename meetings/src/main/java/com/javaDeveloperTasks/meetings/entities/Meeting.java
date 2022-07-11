package com.javaDeveloperTasks.meetings.entities;

import com.javaDeveloperTasks.meetings.enums.MeetingCategory;
import com.javaDeveloperTasks.meetings.enums.MeetingType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="MEETINGS")
public class Meeting {

    //Field declaration
    @Id
    @SequenceGenerator(
            name = "meeting_sequence",
            sequenceName = "meeting_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meeting_sequence"
    )
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="RESPONSIBLE_PERSON")
    private String responsiblePerson;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CATEGORY")
    @Enumerated(EnumType.STRING)
    private MeetingCategory category;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private MeetingType type;

    @Column(name="START_DATE")
    private LocalDate startDate;

    @Column(name="END_DATE")
    private LocalDate endDate;

    @Column(name="ATTENDEES")
    private List<String> attendees;

    public Meeting(String name,
                   String responsiblePerson,
                   String description,
                   MeetingCategory category,
                   MeetingType type,
                   LocalDate startDate,
                   LocalDate endDate,
                   List<String> attendees) {
        this.name = name;
        this.responsiblePerson = responsiblePerson;
        this.description = description;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attendees = attendees;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeetingCategory getCategory() {
        return category;
    }

    public void setCategory(MeetingCategory category) {
        this.category = category;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", attendees=" + attendees +
                '}';
    }
}
