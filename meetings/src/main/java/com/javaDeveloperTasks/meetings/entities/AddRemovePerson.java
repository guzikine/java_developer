package com.javaDeveloperTasks.meetings.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="ADDREMOVEPERSON")
public class AddRemovePerson {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="DATE")
    private LocalDate date;

    public AddRemovePerson(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public AddRemovePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddRemovePerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
