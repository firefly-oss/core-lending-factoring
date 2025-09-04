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


package com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringDebtorDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringDebtorId;

    @FilterableId
    @NotNull(message = "Factoring agreement ID is required")
    private UUID factoringAgreementId;     // Ties to FactoringAgreement

    @FilterableId
    @NotNull(message = "Debtor customer ID is required")
    private UUID debtorCustomerId;         // External reference for the debtor

    @NotBlank(message = "Debtor name is required")
    @Size(min = 1, max = 255, message = "Debtor name must be between 1 and 255 characters")
    private String debtorName;

    @ValidAmount(message = "Debtor credit limit must be a valid positive amount")
    private BigDecimal debtorCreditLimit;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}