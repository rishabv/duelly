package com.duelly.services.admin;

import com.duelly.constants.ErrorMessages;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.entities.Category;
import com.duelly.enums.Status;
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
        category.setCategory(data.getCategory());
        category.setImage(data.getImage());
        category.setStatus(Status.ACTIVE);
        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }

    private boolean validateIfAssigned(Long id) {
        return true;
    }

    public String removeCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorMessages.CATEGORY_NOT_EXIST));
        validateIfAssigned(id);
        categoryRepository.deleteById(id);
        return SuccessMessage.CATEGORY_DELETED;
    }

    public Category getCategoryDetails(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorMessages.CATEGORY_NOT_EXIST));
    }
}
