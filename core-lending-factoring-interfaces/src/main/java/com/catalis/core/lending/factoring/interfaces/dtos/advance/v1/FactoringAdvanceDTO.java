package com.catalis.core.lending.factoring.interfaces.dtos.advance.v1;

import com.catalis.core.utils.annotations.FilterableId;
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
public class FactoringAdvanceDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringAdvanceId;

    @FilterableId
    private Long factoringInvoiceId;   // Ties to FactoringInvoice

    @FilterableId
    private Long transactionId;        // Payment transaction reference

    private BigDecimal advanceAmount;
    private LocalDate advanceDate;
    private Boolean isFinalAdvance;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

