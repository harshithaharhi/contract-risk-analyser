package com.internship.tool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Contract implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract name is required")
    @Column(nullable = false)
    private String contractName;

    @NotBlank(message = "Vendor name is required")
    @Column(nullable = false)
    private String vendorName;

    @NotBlank(message = "Contract type is required")
    @Column(nullable = false)
    private String contractType;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status;

    @NotNull(message = "Contract value is required")
    @Column(nullable = false)
    private Double contractValue;

    @NotNull(message = "Start date is required")
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(length = 1000)
    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}