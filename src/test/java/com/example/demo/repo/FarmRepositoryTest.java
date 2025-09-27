package com.example.demo.repo;

import com.example.demo.service.AccessService;
import com.example.demo.service.FarmService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.Duration;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import({FarmService.class})
class FarmRepositoryTest {

    @Autowired
    private FarmRepository farmRepository;
    @MockitoBean
    private AccessService accessService;
    @Autowired
    private FarmService farmService;

    @Test
    void test() {
        when(accessService.hasAccessToUpdateFarm(any(), any())).thenAnswer(
                invocationOnMock -> {
                    Thread.sleep(2000);
                    return true;
                }
        );

        farmService.updateFarm(
                UUID.randomUUID(),
                1L,
                "new name",
                "new description"
        );
    }

}