package com.duelly.services.Challenge;

import com.duelly.dtos.CategoryDto;
import com.duelly.repositories.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private final ChallengeRepository challengeRepository;
}
