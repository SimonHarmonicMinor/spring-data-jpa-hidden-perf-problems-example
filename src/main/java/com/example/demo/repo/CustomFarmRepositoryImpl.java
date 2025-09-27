package com.example.demo.repo;

import com.example.demo.domain.Chicken;
import com.example.demo.domain.Egg;
import com.example.demo.domain.Farm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomFarmRepositoryImpl implements CustomFarmRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Optional<Farm> findDeepJoinFetch(Long farmId) {
        Farm farm = em.createQuery(
                        """
                                SELECT f FROM Farm f
                                LEFT JOIN FETCH f.chickens
                                WHERE f.id = :farmId""",
                        Farm.class
                ).setParameter("farmId", farmId)
                .getSingleResult();
        em.createQuery("""
                                SELECT c FROM Chicken c
                                LEFT JOIN FETCH c.eggs
                                WHERE c.id IN (:chickenIds)""",
                        Chicken.class
                ).setParameter(
                        "chickenIds",
                        farm.getChickens()
                                .stream()
                                .map(Chicken::getId)
                                .toList()
                )
                .getResultList();
        return Optional.of(farm);
    }
}
