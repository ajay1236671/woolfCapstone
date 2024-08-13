package com.example.project.Inheritance.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ztpc_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
