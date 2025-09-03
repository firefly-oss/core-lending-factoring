package com.firefly.core.lending.factoring.models.entities.advance.v1;

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
@Table("factoring_advance")
public class FactoringAdvance {

    @Id
    @Column("factoring_advance_id")
    private UUID factoringAdvanceId;

    @Column("factoring_invoice_id")
    private UUID factoringInvoiceId; // FK to FactoringInvoice

    @Column("transaction_id")
    private UUID transactionId;      // External reference for the payment

    @Column("advance_amount")
    private BigDecimal advanceAmount;

    @Column("advance_date")
    private LocalDate advanceDate;

    @Column("is_final_advance")
    private Boolean isFinalAdvance;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}