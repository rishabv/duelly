package com.duelly.services.Challenge;

import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.Category;
import com.duelly.entities.Challenge;
import com.duelly.entities.Sponsor;
import com.duelly.entities.User;
import com.duelly.enums.Status;
import com.duelly.repositories.CategoryRepository;
import com.duelly.repositories.ChallengeRepository;
import com.duelly.repositories.SponsorRepository;
import com.duelly.repositories.UserRepository;
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

    @Autowired
    private final ChallengeRepository challengeRepository;

    @Autowired
    private final SponsorRepository sponsorRepository;

    @Autowired
    private final UserRepository userRepository;

    public BasePaginationResponse<ResultResponse<Category>> getAllCategorylist(Pageable pageable){
        return convertMappingPageToResponse(categoryRepository.getAllCategoriesForUser(pageable), pageable);
    }

    private BasePaginationResponse<ResultResponse<Category>> convertMappingPageToResponse(
            Page<Category> categoryPage, Pageable pageable) {
        var categories = categoryPage.getContent();
        System.out.println(categories + " " + pageable.getPageSize() + " " + pageable.getPageNumber());
        return new BasePaginationResponse<>(new ResultResponse<Category>(categories), pageable.getPageSize(), pageable.getPageNumber() ,categoryPage.getTotalPages());
    }

    private void validateChallenge(CreateChallengeRequest body) {
        String validFrom = body.getValidFrom();
        String validTo = body.getValidTo();
        Long category = Long.parseLong(body.getCategory());
        String name = body.getChallengeName();
        Date from = Utils.getDateFromIsoString(validFrom);
        Date to = Utils.getDateFromIsoString(validTo);
        boolean isAlreadyExists = challengeRepository.existsByChallengeName(name);
        if (isAlreadyExists) {
            throw new IllegalArgumentException("Challenge name already exists");
        }
        if (from.equals(to) || from.after(to)) {
            throw new IllegalArgumentException("from date must be before than to Date");
        }
        Optional<Category> categoryObj = categoryRepository.findById(category);
        if (!categoryObj.isPresent()) {
            throw new IllegalArgumentException("Category is invalid");
        }
        Long companyId = Long.parseLong(body.getCompanyId());
        Optional<Sponsor> sponsor = sponsorRepository.findById(companyId);
        if (!sponsor.isPresent()) {
            throw new IllegalArgumentException("Sponsor is invalid");
        }
    }

    public String createChallenge(CreateChallengeRequest body, User user){
        System.out.println(body + " and " + user.getId() + "name is " + user.getFullName());
        this.validateChallenge(body);
        Challenge newChallenge  = new Challenge();
        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(body, newChallenge);
        Long sponsorId = Long.parseLong(body.getCompanyId());
        Optional<Sponsor> foundSponsor = sponsorRepository.findById(sponsorId);
        newChallenge.setCreatedBy(foundUser);
        newChallenge.setCompany(foundSponsor.get());
        challengeRepository.save(newChallenge);
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


    public BasePaginationResponse<ResultResponse<Sponsor>> getAllSponsorlist(Pageable pageable) {
        var list = sponsorRepository.findByStatus(Status.ACTIVE, pageable);
        return new BasePaginationResponse<>(new ResultResponse<Sponsor>(list.getContent()), pageable.getPageSize(), pageable.getPageNumber(), list.getTotalPages());
    }
}
