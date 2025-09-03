package com.firefly.core.lending.factoring.interfaces.dtos.fee.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidInterestRate;
import com.firefly.core.lending.factoring.interfaces.enums.fee.v1.FeeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringFeeDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringFeeId;

    @FilterableId
    @NotNull(message = "Factoring agreement ID is required")
    private UUID factoringAgreementId;  // Ties to FactoringAgreement

    @NotNull(message = "Fee type is required")
    private FeeTypeEnum feeType;        // DISCOUNT_FEE, ADMIN_FEE, etc.

    @NotNull(message = "Fee rate is required")
    @ValidInterestRate(message = "Fee rate must be a valid percentage")
    private BigDecimal feeRate;         // e.g., 0.02 for 2%

    @ValidAmount(message = "Minimum fee must be a valid positive amount")
    private BigDecimal minFee;

    @ValidAmount(message = "Maximum fee must be a valid positive amount")
    private BigDecimal maxFee;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}