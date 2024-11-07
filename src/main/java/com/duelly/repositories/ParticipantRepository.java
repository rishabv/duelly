package com.duelly.repositories;

import com.duelly.Projections.ChallengeLeadersProjection;
import com.duelly.Projections.MyChallengesProjection;
import com.duelly.entities.Challenge;
import com.duelly.entities.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("Select COUNT(p) > 0 FROM Participant p WHERE p.user.id = :userId AND p.challenge.id = :challengeId")
    boolean checkIfAlreadyParticipated(Long challengeId, Long userId);
    @Query("Select p from Participant p Where p.challenge.id = :challengeId")
    List<ChallengeLeadersProjection> findLeaders(Long challengeId);

    @Query(value = "SELECT c FROM participant p JOIN p.challenge c WHERE p.user.id = :userId", nativeQuery = true)
    Page<MyChallengesProjection> findParticipatedChallengesByUser(Pageable pageable, Long userId);



}
