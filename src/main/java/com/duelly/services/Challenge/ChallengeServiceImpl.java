package com.duelly.services.Challenge;

import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.Category;
import com.duelly.entities.Challenge;
import com.duelly.entities.User;
import com.duelly.repositories.CategoryRepository;
import com.duelly.repositories.ChallengeRepository;
import com.duelly.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public BasePaginationResponse<ResultResponse<Category>> getAllCategorylist(Pageable pageable){
        return convertTopMappingPageToResponse(categoryRepository.getAllCategoriesForUser(pageable), pageable);
    }

    private BasePaginationResponse<ResultResponse<Category>> convertTopMappingPageToResponse(
            Page<Category> categoryPage, Pageable pageable) {
        var categories = categoryPage.getContent();
        System.out.println(categories + " " + pageable.getPageSize() + " " + pageable.getPageNumber());
        return new BasePaginationResponse<>(new ResultResponse<Category>(categories), pageable.getPageSize(), pageable.getPageNumber() ,categoryPage.getTotalPages());
    }

    private void validateChallenge(CreateChallengeRequest body) {
        String validFrom = body.getValidFrom();
        String validTo = body.getValidTo();
        Date date = Utils.getDateFromIsoString("2020-02-13T18:51:09.840Z");
        System.out.println(date);
    }

    public String createChallenge(CreateChallengeRequest body, User user){
        System.out.println(body + " and " + user.getId() + "name is " + user.getFullName());
        Challenge newChallenge  = new Challenge();
        this.validateChallenge(body);
        BeanUtils.copyProperties(body, newChallenge);
        return "";
    }

    public String removeCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category foundcategory = category.get();
            foundcategory.setRemoved(true);
            categoryRepository.save(foundcategory);
            return SuccessMessage.CATEGORY_DELETED;
        } else {
            return "Category not found";
        }
    }
}
