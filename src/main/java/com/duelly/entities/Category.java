package com.duelly.entities;

import com.duelly.dtos.CategoryDto;
import com.duelly.enums.Status;
import jakarta.persistence.*;
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
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean removed = false;
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
