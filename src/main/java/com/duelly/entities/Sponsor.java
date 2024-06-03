package com.duelly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String status;
    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name="updated_by", referencedColumnName = "id")
    private User updatedBy;
    }
