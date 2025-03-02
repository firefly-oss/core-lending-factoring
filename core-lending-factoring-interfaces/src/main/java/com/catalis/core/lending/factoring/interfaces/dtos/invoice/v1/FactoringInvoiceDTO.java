package com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.factoring.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.catalis.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
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
public class FactoringInvoiceDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long factoringInvoiceId;

    @FilterableId
    private Long factoringAgreementId;     // Ties to FactoringAgreement

    @FilterableId
    private Long factoringDebtorId;        // Optional link to FactoringDebtor

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private BigDecimal invoiceAmount;
    private BigDecimal assignedAmount;
    private CurrencyCodeEnum currencyCode;
    private InvoiceStatusEnum invoiceStatus;
    private Boolean isAcceptedByDebtor;
    private LocalDate acceptanceDate;

    @FilterableId
    private Long documentReference;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
