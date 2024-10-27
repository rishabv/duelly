package com.duelly.dtos.requests;

import com.duelly.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDto {
    @NotEmpty(message = "Company name is required.")
    private String companyName;
    @NotEmpty(message = "image is required")
    private String image;

    private int userCount;
    private int challengeCount;
    private String status;
    private User createdBy;
    private User updatedBy;
}
