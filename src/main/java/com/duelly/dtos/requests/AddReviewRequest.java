package com.duelly.dtos.requests;

import com.duelly.entities.MediaFile;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddReviewRequest {
    
    @NotBlank(message = "Review text is required")
    @Size(max = 1000, message = "Review text cannot exceed 1000 characters")
    private String text;
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;
    
    @NotNull(message = "Challenge ID is required")
    private Long challengeId;
    
    private String image;
    
    private List<MediaFile> mediaFiles = new ArrayList<>();
}
