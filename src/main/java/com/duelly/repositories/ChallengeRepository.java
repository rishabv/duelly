package com.duelly.repositories;

import com.duelly.Projections.ChallengeDetailsProjection;
import com.duelly.Projections.MyChallengesProjection;
import com.duelly.dtos.responses.ChallengeDetailsResponse;
import com.duelly.entities.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    boolean existsByChallengeName(String name);

    Optional<Challenge> findByChallengeName(String name);

    @Query("SELECT c FROM Challenge c LEFT JOIN c.createdBy user LEFT JOIN c.company company LEFT JOIN c.category category WHERE c.id = :id")
    ChallengeDetailsProjection findChallengeDetails(@Param("id") Long id);

    @Query("Select c from Challenge c Where c.createdBy.id = :userId AND isActive = true")
    Page<MyChallengesProjection> findChallengesById(Pageable pageable, Long userId);
}
