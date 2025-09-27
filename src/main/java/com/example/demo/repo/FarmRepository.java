package com.example.demo.repo;

import com.example.demo.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

public interface FarmRepository extends
        JpaRepository<Farm, Long> {
    @Query(
            """
                    FROM Farm f LEFT JOIN FETCH f.chickens LEFT JOIN FETCH f.cows WHERE f.id = :farmId"""
    )
    Optional<Farm> findWithRelationsById(Long farmId);

    List<Farm> findAllByNameLike(String name);

    @Lock(PESSIMISTIC_WRITE)
    @Query("FROM Farm WHERE id = :id")
    @Transactional
    Optional<Farm> findByIdForUpdate(Long id);

    @Query("""
            FROM Farm f
            LEFT JOIN FETCH f.chickens c
            LEFT JOIN FETCH c.eggs
            WHERE f.id = :farmId""")
    Optional<Farm> findDeepJoinFetch(Long farmId);
}
