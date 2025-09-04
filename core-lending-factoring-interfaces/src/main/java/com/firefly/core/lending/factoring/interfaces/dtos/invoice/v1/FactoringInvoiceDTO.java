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


package com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidCurrencyCode;
import com.firefly.annotations.ValidDate;
import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringInvoiceDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringInvoiceId;

    @FilterableId
    @NotNull(message = "Factoring agreement ID is required")
    private UUID factoringAgreementId;     // Ties to FactoringAgreement

    @FilterableId
    private UUID factoringDebtorId;        // Optional link to FactoringDebtor

    @NotBlank(message = "Invoice number is required")
    @Size(min = 1, max = 100, message = "Invoice number must be between 1 and 100 characters")
    private String invoiceNumber;

    @NotNull(message = "Invoice date is required")
    @ValidDate
    private LocalDate invoiceDate;

    @NotNull(message = "Due date is required")
    @ValidDate
    private LocalDate dueDate;

    @NotNull(message = "Invoice amount is required")
    @ValidAmount(message = "Invoice amount must be a valid positive amount")
    private BigDecimal invoiceAmount;

    @ValidAmount(message = "Assigned amount must be a valid positive amount")
    private BigDecimal assignedAmount;

    @NotNull(message = "Currency code is required")
    @ValidCurrencyCode
    private CurrencyCodeEnum currencyCode;

    @NotNull(message = "Invoice status is required")
    private InvoiceStatusEnum invoiceStatus;

    private Boolean isAcceptedByDebtor;

    @ValidDate
    private LocalDate acceptanceDate;

    @FilterableId
    private UUID documentReference;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
