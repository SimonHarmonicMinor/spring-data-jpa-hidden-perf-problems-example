package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccessService {
    public boolean hasAccessToUpdateFarm(UUID userId, Long farmId) {
        return false;
    }
}
