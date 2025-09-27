package com.example.demo.service;

import com.example.demo.PgTestContainersConfig;
import com.example.demo.domain.Farm;
import com.example.demo.repo.FarmRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest
@Import({FarmService.class, PgTestContainersConfig.class})
@Slf4j
class FarmServiceTest {
    @MockitoBean
    private AccessService accessService;
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private FarmService farmService;

    @Test
    void logConnectionAcquiring() {
        when(accessService.hasAccessToUpdateFarm(any(), any())).thenAnswer(
                invocationOnMock -> {
                    Thread.sleep(2000);
                    return true;
                }
        );

        farmRepository.save(new Farm("name", "desc"));

        log.info("Starting business code");
        farmService.updateFarm(
                UUID.randomUUID(),
                1L,
                "new name",
                "new description"
        );
        log.info("Finishing business code");
    }

}