package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Egg {
    @Id
    @GeneratedValue(generator = "egg_seq")
    @SequenceGenerator(allocationSize = 500, sequenceName = "egg_id_seq", name = "egg_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chicken chicken;

    private String name;

    public Egg(Chicken chicken, String name) {
        this.chicken = chicken;
        this.name = name;
    }
}
