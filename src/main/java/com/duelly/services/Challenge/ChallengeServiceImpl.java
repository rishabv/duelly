package com.duelly.services.Challenge;

import com.duelly.Projections.ChallengeDetailsProjection;
import com.duelly.Projections.ChallengeLeadersProjection;
import com.duelly.Projections.MyChallengesProjection;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.requests.ChallengeRequest;
import com.duelly.dtos.requests.CreateChallengeRequest;
import com.duelly.dtos.requests.ParticipateRequest;
import com.duelly.dtos.requests.UpdateChallengePatchRequest;
import com.duelly.dtos.responses.BasePaginationResponse;
import com.duelly.dtos.responses.ChallengeDetailsResponse;
import com.duelly.dtos.responses.ResultResponse;
import com.duelly.entities.*;
import com.duelly.enums.Status;
import com.duelly.enums.UserRole;
import com.duelly.repositories.*;
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

    @Autowired
    private final ParticipantRepository participantRepository;

    public BasePaginationResponse<ResultResponse<Category>> getAllCategorylist(Pageable pageable) {
        return convertMappingPageToResponse(categoryRepository.getAllCategoriesForUser(pageable), pageable);
    }

    private BasePaginationResponse<ResultResponse<Category>> convertMappingPageToResponse(
            Page<Category> categoryPage, Pageable pageable) {
        var categories = categoryPage.getContent();
        System.out.println(categories + " " + pageable.getPageSize() + " " + pageable.getPageNumber());
        return new BasePaginationResponse<>(new ResultResponse<Category>(categories), pageable.getPageSize(), pageable.getPageNumber(), categoryPage.getTotalPages());
    }

    private void validateChallenge(ChallengeRequest body, Challenge challenge) {
        if (body instanceof CreateChallengeRequest createRequest) {
            String validFrom = createRequest.getValidFrom();
            String validTo = createRequest.getValidTo();
            Long category = Long.parseLong(createRequest.getCategory());
            String name = createRequest.getChallengeName();
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
            Long companyId = Long.parseLong(createRequest.getCompanyId());
            Optional<Sponsor> sponsor = sponsorRepository.findById(companyId);
            if (!sponsor.isPresent()) {
                throw new IllegalArgumentException("Sponsor is invalid");
            }
        } else if (body instanceof UpdateChallengePatchRequest updateRequest) {
            Optional<String> validFrom = updateRequest.getValidFrom();
            Optional<String> validTo = updateRequest.getValidTo();
            if (validFrom.isPresent()) {
                System.out.println(validFrom);
                Date from = Utils.getDateFromIsoString(validFrom.get());
                Date to = Utils.getDateFromIsoString(challenge.getValidTo());
                if (from.equals(to) || from.after(to)) {
                    throw new IllegalArgumentException("from date must be before than to Date");
                }
            }
            if (validTo.isPresent()) {
                Date from = Utils.getDateFromIsoString(challenge.getValidFrom());
                Date to = Utils.getDateFromIsoString(validTo.get());
                if (from.equals(to) || from.after(to)) {
                    throw new IllegalArgumentException("from date must be before than to Date");
                }
            }
            Optional<String> name = updateRequest.getChallengeName();
            if (name.isPresent()) {
                Optional<Challenge> foundChallenge = challengeRepository.findByChallengeName(name.get());
                if (foundChallenge.isPresent() && foundChallenge.get().getId() != challenge.getId()) {
                    throw new IllegalArgumentException("Challenge name already exists");
                }
            }
        }
    }

    public String createChallenge(CreateChallengeRequest body, User user) {
        System.out.println(body + " and " + user.getId() + "name is " + user.getFullName());
        this.validateChallenge(body, null);
        Challenge newChallenge = new Challenge();
        Participant participant = new Participant();
        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(body, newChallenge);
        Long sponsorId = Long.parseLong(body.getCompanyId());
        Optional<Sponsor> foundSponsor = sponsorRepository.findById(sponsorId);
        newChallenge.setCreatedBy(foundUser);
        newChallenge.setCompany(foundSponsor.get());
        Long categoryId = Long.parseLong(body.getCategory());
        Optional<Category> category = categoryRepository.findById(categoryId);
        newChallenge.setCategory(category.get());
        System.out.println(newChallenge.toString());
        newChallenge.setActive(true);
        Challenge challenge = challengeRepository.save(newChallenge);
        participant.setChallenge(challenge);
        participant.setTitle(challenge.getChallengeName());
        participant.setVideoUrl(body.getVideoUrl());
        participant.setUser(foundUser);
        participant.setVideoDesc(body.getChellengeRequirement());
        participantRepository.save(participant);
        return "";
    }

    public String removeCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
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

    public ChallengeDetailsProjection getChallengeDetails(Long id) {
        ChallengeDetailsProjection details = challengeRepository.findChallengeDetails(id);
        return details;
    }

    public String updateChallenge(String id, UpdateChallengePatchRequest request, User user) {
        Long challengeId = Long.parseLong(id);
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("Challenge not found"));
        User creator = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid request"));

        if (creator.getRole() != UserRole.ADMIN && challenge.getCreatedBy().getId() != user.getId()) {
            throw new IllegalArgumentException("You are not authorized to update this challenge");
        }
        this.validateChallenge(request, challenge);
        if (request.getCompanyId().isPresent()) {
            Long companyId = Long.parseLong(request.getCompanyId().get());
            Optional<Sponsor> sponsor = sponsorRepository.findById(companyId);
            if (!sponsor.isPresent()) {
                throw new IllegalArgumentException("Sponsor is invalid");
            } else {
                challenge.setCompany(sponsor.get());
            }
        }
        if (request.getCategory().isPresent()) {
            Long category = Long.parseLong(request.getCategory().get());
            Optional<Category> categoryObj = categoryRepository.findById(category);
            if (!categoryObj.isPresent()) {
                throw new IllegalArgumentException("Category is invalid");
            } else {
                challenge.setCategory(categoryObj.get());
            }
        }
        request.getChallengeName().ifPresent(challenge::setChallengeName);
        request.getChallengeRequirement().ifPresent(challenge::setChallengeRequirement);
        request.getThumbnailImageUrlName().ifPresent(challenge::setThumbnailImageUrlName);
        request.getVideoUrl().ifPresent(challenge::setVideoUrl);
        request.getVideoUrlName().ifPresent(challenge::setVideoUrlName);
        request.getValidFrom().ifPresent(challenge::setValidFrom);
        request.getValidTo().ifPresent(challenge::setValidTo);
        request.getTermConditions().ifPresent(challenge::setTermConditions);
        request.getIsPrice().ifPresent(challenge::setPrice);
        request.getPriceName().ifPresent(challenge::setPriceName);
        request.getPriceImage().ifPresent(challenge::setPriceImage);
        request.getIsActive().ifPresent(challenge::setActive);
        challengeRepository.save(challenge);
        return "Challenge updated successfully";
    }

    public BasePaginationResponse<ResultResponse<MyChallengesProjection>> getMyChallenges(Pageable pageable, User user) {
        Page<MyChallengesProjection> list = challengeRepository.findChallengesById(pageable, user.getId());
        return new BasePaginationResponse<>(new ResultResponse<MyChallengesProjection>(list.getContent()), pageable.getPageSize(), pageable.getPageNumber(), list.getTotalPages());
    }

    public String participateChallenge(ParticipateRequest request, User user) {
        Long challengeId = Long.parseLong(request.getChallengeId());
        Long companyId = Long.parseLong(request.getCompanyId());
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new IllegalArgumentException("challenge id is invalid"));
        boolean alreadyJoinedChallenge = participantRepository.checkIfAlreadyParticipated(challengeId, user.getId());
        if (alreadyJoinedChallenge){
            throw new IllegalArgumentException("Already participated");
        }
        User createdBy = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("invalid user"));
        Sponsor company = sponsorRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Invalid company id"));
        Participant participant = new Participant();
        BeanUtils.copyProperties(request, participant);
        participant.setChallenge(challenge);
        participant.setUser(createdBy);
        participant.setSponsor(company);
        participantRepository.save(participant);
        return "Joined successfully";
    }

    public List<ChallengeLeadersProjection> getChallengeLeaders(String id) {
        Long challengId = Long.parseLong(id);
        List<ChallengeLeadersProjection> list = participantRepository.findLeaders(challengId);
        return list;
    }
}
