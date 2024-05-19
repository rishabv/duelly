package com.duelly.services.admin;

import com.duelly.dtos.CategoryDto;
import com.duelly.entities.Category;
import com.duelly.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Override
    public Category createCategory(CategoryDto data){
        Category category = new Category();
        category.setName(data.getCategory());
        category.setName(data.getImage());
        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }
}
