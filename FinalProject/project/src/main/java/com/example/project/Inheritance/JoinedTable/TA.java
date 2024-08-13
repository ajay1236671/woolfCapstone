package com.example.project.Inheritance.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zjo_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private double averageRating;
}
