package com.duelly.entities;

import com.duelly.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sponsor")
public class Sponsor extends BaseEntity {
    private String companyName;
    private String image;
    private int userCount;
    private int challengeCount;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name="updated_by", referencedColumnName = "id")
    private User updatedBy;
    }
