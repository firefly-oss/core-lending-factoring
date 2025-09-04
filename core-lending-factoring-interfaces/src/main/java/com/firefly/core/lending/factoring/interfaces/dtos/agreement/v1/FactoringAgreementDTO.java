/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDate;
import com.firefly.annotations.ValidInterestRate;
import com.firefly.core.lending.factoring.interfaces.enums.agreement.v1.AgreementStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringAgreementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringAgreementId;

    @FilterableId
    @NotNull(message = "Contract ID is required")
    private UUID contractId;               // External reference to Contract domain

    @FilterableId
    @NotNull(message = "Customer ID is required")
    private UUID customerId;               // The factoring client

    @NotNull(message = "Agreement status is required")
    private AgreementStatusEnum agreementStatus;

    @NotNull(message = "Start date is required")
    @ValidDate
    private LocalDate startDate;

    @ValidDate
    private LocalDate endDate;

    @ValidAmount(message = "Credit limit must be a valid positive amount")
    private BigDecimal creditLimit;

    @NotNull(message = "Recourse flag is required")
    private Boolean recourse;              // true = recourse factoring

    @ValidInterestRate(message = "Advance rate must be a valid percentage between 0 and 100")
    private BigDecimal advanceRate;        // e.g. 80% = 80.00

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

