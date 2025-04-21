package com.catalis.core.lending.factoring.interfaces.dtos.fee.v1;

import com.catalis.core.lending.factoring.interfaces.enums.fee.v1.FeeTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class FactoringFeeDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringFeeId;

    @FilterableId
    private Long factoringAgreementId;  // Ties to FactoringAgreement

    private FeeTypeEnum feeType;        // DISCOUNT_FEE, ADMIN_FEE, etc.
    private BigDecimal feeRate;         // e.g., 0.02 for 2%
    private BigDecimal minFee;
    private BigDecimal maxFee;
    private Boolean isActive;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}