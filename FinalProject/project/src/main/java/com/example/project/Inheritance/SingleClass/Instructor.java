package com.example.project.Inheritance.SingleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zs_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private boolean isHandsome;
}
