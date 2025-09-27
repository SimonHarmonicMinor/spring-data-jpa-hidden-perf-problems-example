package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Farm {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "farm")
    private Set<Chicken> chickens;

    @OneToMany(mappedBy = "farm")
    private List<Cow> cows;

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isOpen() {
        return true;
    }
}
