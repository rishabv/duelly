package com.duelly.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "votes")
public class Vote extends BaseEntity {
    private int skills;
    private int presentation;
    private int difficulty;
    private int technique;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name="votedBy_id")
    private User votedBy;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name="participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name="challenge_id")
    private Challenge challenge;
}
