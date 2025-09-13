package com.duelly.repositories;

import com.duelly.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    @Query("SELECT r FROM Review r WHERE r.challenge.id = :challengeId ORDER BY r.creationDate DESC")
    List<Review> findByChallengeIdOrderByCreatedAtDesc(@Param("challengeId") Long challengeId);
    
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.challenge.id = :challengeId")
    Optional<Review> findByUserIdAndChallengeId(@Param("userId") Long userId, @Param("challengeId") Long challengeId);
    
    boolean existsByUserIdAndChallengeId(Long userId, Long challengeId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.challenge.id = :challengeId")
    Long countByChallengeId(@Param("challengeId") Long challengeId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.challenge.id = :challengeId")
    Double getAverageRatingByChallengeId(@Param("challengeId") Long challengeId);
}
