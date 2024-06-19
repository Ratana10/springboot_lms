package com.kongsun.leanring.system.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SoftDelete
public abstract class AuditingEntity {
    @CreatedDate
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(
            name = "updated_at",
            insertable = false
    )
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(
            name = "created_by",
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(
            name = "updated_by",
            insertable = false
    )
    private Long updatedBy;
}
