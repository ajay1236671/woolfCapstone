package com.example.project.Inheritance.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zm_ta")
public class TA extends User {
    private double averageRating;
}
