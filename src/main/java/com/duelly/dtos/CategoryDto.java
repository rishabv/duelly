package com.duelly.dtos;

import com.duelly.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
        private Long id;
        @NotEmpty(message = "Category name is required")
        private String category;
        private Status status;
        @NotEmpty(message = "Category Image is required")
        private String image;
        private int addCount = 0;
}
