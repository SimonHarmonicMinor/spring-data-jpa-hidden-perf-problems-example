package com.example.demo.service;

import com.example.demo.domain.Farm;
import com.example.demo.repo.FarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FarmService {
    private final FarmRepository farmRepository;
    private final AccessService accessService;

    @Transactional
    public Farm updateFarm(UUID currentUserId,
                           Long farmId,
                           String name,
                           String description) {
        log.info("Accessing remote service to check access");
        if (!accessService.hasAccessToUpdateFarm(currentUserId, farmId)) {
            throw new AccessException("No access to update farm=" + farmId);
        }
        log.info("Access granted successfully");
        Farm farm = farmRepository.findByIdForUpdate(farmId).orElseThrow();
        if (farm.isOpen()) {
            farm.update(name, description);
            return farmRepository.save(farm);
        } else {
            throw new FarmUpdateException("Cannot update farm=" + farmId + " because it's already closed");
        }
    }
}
