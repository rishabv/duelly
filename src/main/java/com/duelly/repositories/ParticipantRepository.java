package com.duelly.repositories;

import com.duelly.Projections.ChallengeLeadersProjection;
import com.duelly.entities.Challenge;
import com.duelly.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("Select COUNT(p) > 0 FROM Participant p WHERE p.user.id = :userId AND p.challenge.id = :challengeId")
    boolean checkIfAlreadyParticipated(Long challengeId, Long userId);
    @Query("Select p from Participant p Where p.challenge.id = :challengeId")
    List<ChallengeLeadersProjection> findLeaders(Long challengeId);
}
