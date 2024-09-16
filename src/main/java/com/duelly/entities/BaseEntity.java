package com.duelly.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime creationDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDateTime lastModifiedDate;
    @Column(columnDefinition = "tinyint(1) default 0")
    protected boolean isDeleted = false;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    protected boolean isActive = Boolean.TRUE;
}
