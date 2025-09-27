package com.example.demo.repo;

import com.example.demo.domain.Cow;
import com.example.demo.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CowRepository extends JpaRepository<Cow, Long> {
    List<Cow> findAllByFarm(Farm farm);
}
