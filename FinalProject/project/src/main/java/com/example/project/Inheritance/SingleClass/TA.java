package com.example.project.Inheritance.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zs_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private double averageRating;
}
