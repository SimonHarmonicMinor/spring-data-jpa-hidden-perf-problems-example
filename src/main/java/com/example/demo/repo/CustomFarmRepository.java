package com.example.demo.repo;

import com.example.demo.domain.Farm;

import java.util.List;
import java.util.Optional;

public interface CustomFarmRepository {
    Optional<Farm> findDeepJoinFetch(Long farmId);
}
