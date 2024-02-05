package com.example.SpringBatchTutorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TARGET_PERSON")
public class TargetPerson {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;

    public TargetPerson() {
    }

    public TargetPerson(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "firstName: " + firstName + ", lastName: " + lastName+ ", birthDate: " + birthDate;
    }

}