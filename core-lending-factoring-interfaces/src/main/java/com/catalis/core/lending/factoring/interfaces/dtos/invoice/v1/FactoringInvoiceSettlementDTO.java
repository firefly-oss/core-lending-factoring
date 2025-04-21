package com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1;

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
public class FactoringInvoiceSettlementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringInvoiceSettlementId;

    @FilterableId
    private Long factoringInvoiceId;    // Ties to FactoringInvoice

    @FilterableId
    private Long transactionId;         // Payment from debtor reference

    private LocalDate settlementDate;
    private BigDecimal settlementAmount;
    private BigDecimal feesDeducted;
    private BigDecimal netToClient;
    private Boolean isClosed;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}