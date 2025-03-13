package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "farm")
    private List<Chicken> chickens;

    @OneToMany(mappedBy = "farm")
    private List<Cow> cows;
}
