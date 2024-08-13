package com.example.project.Inheritance.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ztpc_instructor")
public class Instructor extends User{
    private boolean isHandsome;
}
