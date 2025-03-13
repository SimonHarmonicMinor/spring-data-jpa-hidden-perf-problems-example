package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chicken chicken;

    private String name;
}
