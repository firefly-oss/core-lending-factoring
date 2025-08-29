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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("factoring_advance")
public class FactoringAdvance {

    @Id
    @Column("factoring_advance_id")
    private Long factoringAdvanceId;

    @Column("factoring_invoice_id")
    private Long factoringInvoiceId; // FK to FactoringInvoice

    @Column("transaction_id")
    private Long transactionId;      // External reference for the payment

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