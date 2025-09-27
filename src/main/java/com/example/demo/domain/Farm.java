package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Farm {
    @Id
    @GeneratedValue(generator = "farm_seq")
    @SequenceGenerator(allocationSize = 500, sequenceName = "farm_id_seq", name = "farm_seq")
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.PERSIST)
    private List<Chicken> chickens;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.PERSIST)
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
