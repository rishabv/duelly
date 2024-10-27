package com.duelly.services.admin;

import com.duelly.constants.ErrorMessages;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.SponsorDto;
import com.duelly.entities.Category;
import com.duelly.entities.Sponsor;
import com.duelly.enums.Status;
import com.duelly.repositories.CategoryRepository;
import com.duelly.repositories.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final SponsorRepository sponsorRepository;
    @Override
    public Category createCategory(CategoryDto data){
        boolean isAlreadyExists = categoryRepository.existsByCategory(data.getCategory());
        if(isAlreadyExists){
            throw new IllegalArgumentException("Category already exists");
        }
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

    public String createSponsor(SponsorDto data) {
        boolean isAlreadyExists = sponsorRepository.existsByCompanyName(data.getCompanyName());
        if(isAlreadyExists) {
            throw new IllegalArgumentException("Company name already exists");
        }
        Sponsor sponsor = new Sponsor();
        BeanUtils.copyProperties(data, sponsor);
        sponsor.setUserCount(0);
        sponsor.setChallengeCount(0);
        sponsor.setStatus(Status.ACTIVE);
        sponsorRepository.save(sponsor);
        return "Sponsor created.";
    }
}
