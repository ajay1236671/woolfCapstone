package com.example.project.Inheritance.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zm_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
