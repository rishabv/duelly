package com.duelly.services.admin;

import com.duelly.dtos.CategoryDto;
import com.duelly.entities.Category;

public interface AdminService {
    Category createCategory(CategoryDto data);
}
