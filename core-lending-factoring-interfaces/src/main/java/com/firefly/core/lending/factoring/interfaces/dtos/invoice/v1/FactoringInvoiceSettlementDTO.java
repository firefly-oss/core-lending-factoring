package com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDate;
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
public class FactoringInvoiceSettlementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID factoringInvoiceSettlementId;

    @FilterableId
    @NotNull(message = "Factoring invoice ID is required")
    private UUID factoringInvoiceId;    // Ties to FactoringInvoice

    @FilterableId
    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;         // Payment from debtor reference

    @NotNull(message = "Settlement date is required")
    @ValidDate
    private LocalDate settlementDate;

    @NotNull(message = "Settlement amount is required")
    @ValidAmount(message = "Settlement amount must be a valid positive amount")
    private BigDecimal settlementAmount;

    @ValidAmount(message = "Fees deducted must be a valid positive amount")
    private BigDecimal feesDeducted;

    @ValidAmount(message = "Net to client must be a valid positive amount")
    private BigDecimal netToClient;

    @NotNull(message = "Closed flag is required")
    private Boolean isClosed;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}