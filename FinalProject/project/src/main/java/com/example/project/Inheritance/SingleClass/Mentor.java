package com.example.project.Inheritance.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zs_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
