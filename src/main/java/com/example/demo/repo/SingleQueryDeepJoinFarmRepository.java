package com.example.demo.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("single-query")
@Slf4j
public class SingleQueryDeepJoinFarmRepository implements CustomFarmRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void deepJoinFetch() {
        Long count = em.createQuery(
                """
                        SELECT COUNT(e.id) FROM Egg e
                        JOIN e.chicken c
                        JOIN c.farm""",
                long.class
        ).getSingleResult();
        log.info("Eggs + chickens + farms count {}", count);
    }
}
