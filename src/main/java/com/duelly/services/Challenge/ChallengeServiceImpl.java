package com.duelly.services.Challenge;

import com.duelly.dtos.CategoryDto;
import com.duelly.entities.Category;
import com.duelly.repositories.CategoryRepository;
import com.duelly.repositories.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategorylist(){
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }
}
