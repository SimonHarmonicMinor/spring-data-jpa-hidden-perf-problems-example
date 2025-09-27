package com.example.demo.repo;

import com.example.demo.PgTestContainersConfig;
import com.example.demo.domain.Chicken;
import com.example.demo.domain.Egg;
import com.example.demo.domain.Farm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@DataJpaTest(showSql = false)
@Testcontainers
@Import({PgTestContainersConfig.class, TwoQueriesDeepJoinFarmRepository.class, SingleQueryDeepJoinFarmRepository.class})
@Transactional(propagation = NOT_SUPPORTED)
class CustomFarmRepositoryTest {
    @Autowired
    private List<CustomFarmRepository> repos;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private Environment environment;

    @Test
    void test() {
        String property = environment.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size");
        System.out.println(property);
        transactionTemplate.executeWithoutResult((status) -> {
            for (int i = 0; i < 20; i++) {
                Farm farm = new Farm("name", "desc");
                for (int j = 0; j < 10; j++) {
                    Chicken chicken = new Chicken("chicken", farm);
                    farm.getChickens().add(chicken);
                    for (int k = 0; k < 5; k++) {
                        Egg egg = new Egg(chicken, "egg");
                        chicken.getEggs().add(egg);
                    }
                }
                em.persist(farm);
            }

        });

        for (CustomFarmRepository repo : repos) {
            repo.deepJoinFetch();
        }
    }
}