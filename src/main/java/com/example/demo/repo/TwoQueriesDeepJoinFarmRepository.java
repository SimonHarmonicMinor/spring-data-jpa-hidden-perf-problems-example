package com.example.demo.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@Qualifier("two-queries")
public class TwoQueriesDeepJoinFarmRepository implements CustomFarmRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void deepJoinFetch() {
        Long count = em.createQuery(
                """
                        SELECT COUNT(f.id) FROM Farm f
                        LEFT JOIN f.chickens""",
                long.class
        ).getSingleResult();
        log.info("Farm + chickens count {}", count);
        count = em.createQuery("""
                                SELECT COUNT(c.id) FROM Chicken c
                                LEFT JOIN c.eggs""",
                        long.class)
                .getSingleResult();
        log.info("Chickens + eggs count {}", count);
    }
}
