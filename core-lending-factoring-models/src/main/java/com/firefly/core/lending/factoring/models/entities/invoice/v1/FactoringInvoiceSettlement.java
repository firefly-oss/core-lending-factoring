package com.firefly.core.lending.factoring.models.entities.invoice.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("factoring_invoice_settlement")
public class FactoringInvoiceSettlement {

    @Id
    @Column("factoring_invoice_settlement_id")
    private UUID factoringInvoiceSettlementId;

    @Column("factoring_invoice_id")
    private UUID factoringInvoiceId; // FK to FactoringInvoice

    @Column("transaction_id")
    private UUID transactionId;      // Payment from debtor (external ref)

    @Column("settlement_date")
    private LocalDate settlementDate;

    @Column("settlement_amount")
    private BigDecimal settlementAmount;

    @Column("fees_deducted")
    private BigDecimal feesDeducted;

    @Column("net_to_client")
    private BigDecimal netToClient;

    @Column("is_closed")
    private Boolean isClosed;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
