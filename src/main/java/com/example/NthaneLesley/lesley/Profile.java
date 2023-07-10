package com.example.NthaneLesley.lesley;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;

public class Profile {
    @Id
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "student_sequemce",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_sequence"
    )
    private long id;
    private String name;
    private String surname;
    private String httpImageLink;

    public Profile() {
    }

    public Profile(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Profile(long id,String name, String surname, String httpImageLink) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.httpImageLink = httpImageLink;
    }

    public Profile(String name, String surname, String httpImageLink) {
        this.name = name;
        this.surname = surname;
        this.httpImageLink = httpImageLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHttpImageLink() {
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }

}
