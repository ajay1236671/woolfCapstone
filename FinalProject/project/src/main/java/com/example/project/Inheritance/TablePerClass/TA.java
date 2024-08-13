package com.example.project.Inheritance.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ztpc_ta")
public class TA extends User {
    private double averageRating;
}
