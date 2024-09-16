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

import java.util.Optional;

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
        Optional<Category> category = categoryRepository.findById(id);
        validateIfAssigned(id);
        if(category.isPresent()){
            Category foundcategory = category.get();
            foundcategory.setRemoved(true);
            categoryRepository.save(foundcategory);
            return SuccessMessage.CATEGORY_DELETED;
        } else {
            return "Category not found";
        }
    }

    public String activateCategory(Long id, boolean isActive) {
        Optional<Category> category = categoryRepository.findById(id);
        // validateIfAssigned(id);
        if(category.isPresent()){
            Category foundcategory = category.get();
            if(foundcategory.isRemoved()) {
                throw new IllegalArgumentException("Category not found");
            }
            foundcategory.setActive(isActive);
            categoryRepository.save(foundcategory);
            return SuccessMessage.CATEGORY_DELETED;
        } else {
            return "Category not found";
        }
    }

    public Category getCategoryDetails(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorMessages.CATEGORY_NOT_EXIST));
    }
}
