package com.duelly.entities;

import com.duelly.dtos.CategoryDto;
import com.duelly.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="categories")
public class Category extends BaseEntity {
    private String category;
    private String image;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private boolean isRemoved = false;
    private int addCount = 0;
    private boolean isTop = false;
    public CategoryDto getCategoryDto() {
        CategoryDto categoryObj = new CategoryDto();
        categoryObj.setId(id);
        categoryObj.setCategory(category);
        categoryObj.setImage(image);
        categoryObj.setStatus(status);
        return categoryObj;
    }

}
