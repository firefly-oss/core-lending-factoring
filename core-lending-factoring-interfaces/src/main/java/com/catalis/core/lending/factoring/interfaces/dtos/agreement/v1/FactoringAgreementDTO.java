package com.catalis.core.lending.factoring.interfaces.dtos.agreement.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.factoring.interfaces.enums.agreement.v1.AgreementStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringAgreementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringAgreementId;

    @FilterableId
    private Long contractId;               // External reference to Contract domain

    @FilterableId
    private Long customerId;               // The factoring client

    private AgreementStatusEnum agreementStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal creditLimit;
    private Boolean recourse;              // true = recourse factoring
    private BigDecimal advanceRate;        // e.g. 80% = 80.00
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

