package com.duelly.repositories;

import com.duelly.entities.Category;
import com.duelly.entities.Sponsor;
import com.duelly.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    boolean existsByCompanyName(String name);

    Page<Sponsor> findByStatus(Status status, Pageable pageable);
}
