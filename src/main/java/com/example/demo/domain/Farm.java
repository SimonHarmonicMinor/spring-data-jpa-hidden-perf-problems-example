package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Farm {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "farm")
    private List<Chicken> chickens;

    @OneToMany(mappedBy = "farm")
    private List<Cow> cows;

    public Farm(String name, String description) {
        this.name = name;
        this.description = description;
        this.chickens = new ArrayList<>();
        this.cows = new ArrayList<>();
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isOpen() {
        return true;
    }
}
