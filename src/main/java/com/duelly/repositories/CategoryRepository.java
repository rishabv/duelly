package com.duelly.repositories;

import com.duelly.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("Select t from Category t where t.removed = false AND isActive=true")
    Page<Category> getAllCategoriesForUser(Pageable pageable);
}
