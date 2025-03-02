package com.catalis.core.lending.factoring.models.entities.invoice.v1;

import com.catalis.core.lending.factoring.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.catalis.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
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
@Table("factoring_invoice")
public class FactoringInvoice {

    @Id
    @Column("factoring_invoice_id")
    private Long factoringInvoiceId;

    @Column("factoring_agreement_id")
    private Long factoringAgreementId; // FK to FactoringAgreement

    @Column("factoring_debtor_id")
    private Long factoringDebtorId;    // Optional FK to FactoringDebtor

    @Column("invoice_number")
    private String invoiceNumber;

    @Column("invoice_date")
    private LocalDate invoiceDate;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("invoice_amount")
    private BigDecimal invoiceAmount;

    @Column("assigned_amount")
    private BigDecimal assignedAmount; // The portion actually factored

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("invoice_status")
    private InvoiceStatusEnum invoiceStatus;

    @Column("is_accepted_by_debtor")
    private Boolean isAcceptedByDebtor;

    @Column("acceptance_date")
    private LocalDate acceptanceDate;

    @Column("document_reference")
    private Long documentReference;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}