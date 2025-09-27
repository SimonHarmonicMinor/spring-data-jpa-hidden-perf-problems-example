package com.example.demo.repo;

import com.example.demo.domain.Chicken;
import com.example.demo.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChickenRepository extends JpaRepository<Chicken, Long> {
    List<Chicken> findAllByFarm(Farm farm);
}
