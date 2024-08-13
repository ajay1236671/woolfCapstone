package com.example.project.Inheritance.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zm_instructor")
public class Instructor extends User {
    private boolean isHandsome;
}
