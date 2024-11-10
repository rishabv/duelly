package com.duelly.repositories;

import com.duelly.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVotedByIdAndParticipantId(Long userId, Long participantId);
}
