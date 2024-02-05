package com.example.SpringBatchTutorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "SOURCE_PERSON")
public class SourcePerson {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;

    public SourcePerson() {
    }

    public SourcePerson(String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "firstName: " + firstName + ", lastName: " + lastName+ ", birthDate: " + birthDate;
    }

}
