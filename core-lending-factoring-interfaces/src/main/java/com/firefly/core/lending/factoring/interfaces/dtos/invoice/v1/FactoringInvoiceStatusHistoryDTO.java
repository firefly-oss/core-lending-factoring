package com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoringInvoiceStatusHistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringInvoiceStatusHistoryId;

    @FilterableId
    private Long factoringInvoiceId;    // Ties to FactoringInvoice

    private InvoiceStatusEnum oldStatus;
    private InvoiceStatusEnum newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
