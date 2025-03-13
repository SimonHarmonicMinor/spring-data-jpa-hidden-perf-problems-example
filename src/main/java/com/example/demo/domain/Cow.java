package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Cow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Farm farm;
}
