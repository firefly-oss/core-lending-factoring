package com.catalis.core.lending.factoring.interfaces.dtos.debtor.v1;

import com.catalis.common.core.filters.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringDebtorDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringDebtorId;

    @FilterableId
    private Long factoringAgreementId;     // Ties to FactoringAgreement

    @FilterableId
    private Long debtorCustomerId;         // External reference for the debtor

    private String debtorName;
    private BigDecimal debtorCreditLimit;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}