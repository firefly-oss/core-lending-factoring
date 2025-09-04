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

import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringInvoiceStatusHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringInvoiceStatusHistoryId;

    @FilterableId
    private UUID factoringInvoiceId;    // Ties to FactoringInvoice

    private InvoiceStatusEnum oldStatus;
    private InvoiceStatusEnum newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
