package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chicken {
    @Id
    @GeneratedValue(generator = "chicken_seq")
    @SequenceGenerator(allocationSize = 500, sequenceName = "chicken_id_seq", name = "chicken_seq")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Farm farm;

    @OneToMany(mappedBy = "chicken", cascade = CascadeType.PERSIST)
    private List<Egg> eggs;

    public Chicken(String name, Farm farm) {
        this.name = name;
        this.farm = farm;
        this.eggs = new ArrayList<>();
    }
}
