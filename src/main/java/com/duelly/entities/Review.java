package com.duelly.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="reviews", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "challenge_id"})
})
public class Review extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", referencedColumnName = "id", nullable = false)
    private Challenge challenge;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    
    @Column(length = 1000, nullable = false)
    @Size(max = 1000, message = "Review text cannot exceed 1000 characters")
    private String text;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(nullable = false)
    private int rating;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_id")
    private List<MediaFile> mediaFiles = new ArrayList<>();
    
    private int helpfulScore = 0;
}
