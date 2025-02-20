package com.duelly.services.admin;

import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.SponsorDto;
import com.duelly.entities.Category;

public interface AdminService {
    Category createCategory(CategoryDto data);

    String removeCategory(Long id);

    Category getCategoryDetails(Long id);

    String activateCategory(Long id, boolean isActive);

    String createSponsor(SponsorDto sponsorDto);
}
