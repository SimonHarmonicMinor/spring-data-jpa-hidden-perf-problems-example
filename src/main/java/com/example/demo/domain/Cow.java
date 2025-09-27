package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Cow {
    @Id
    @GeneratedValue(generator = "cow_seq")
    @SequenceGenerator(allocationSize = 500, sequenceName = "cow_id_seq", name = "cow_seq")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Farm farm;
}
