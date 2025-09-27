package com.example.demo.service;

import com.example.demo.domain.Chicken;
import com.example.demo.domain.Cow;
import com.example.demo.domain.Farm;

import java.util.List;

public record FarmResponse(
        Long id,
        String name,
        String description,
        List<ChickenResponse> chickens,
        List<CowResponse> cows
) {
    public FarmResponse(Farm farm) {
        this(
                farm.getId(),
                farm.getName(),
                farm.getDescription(),
                farm.getChickens().stream().map(ChickenResponse::new).toList(),
                farm.getCows().stream().map(CowResponse::new).toList()
        );
    }

    public record ChickenResponse(
            Long id,
            String name
    ) {
        public ChickenResponse(Chicken chicken) {
            this(chicken.getId(), chicken.getName());
        }
    }

    public record CowResponse(
            Long id,
            String name
    ) {
        public CowResponse(Cow cow) {
            this(cow.getId(), cow.getName());
        }
    }
}
