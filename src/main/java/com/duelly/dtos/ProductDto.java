package com.restaurant.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String image;

    private Long categoryId;
    private String categoryName;
}
