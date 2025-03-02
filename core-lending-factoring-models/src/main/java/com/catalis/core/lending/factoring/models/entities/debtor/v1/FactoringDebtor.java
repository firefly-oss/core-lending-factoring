package com.catalis.core.lending.factoring.models.entities.debtor.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("factoring_debtor")
public class FactoringDebtor {

    @Id
    @Column("factoring_debtor_id")
    private Long factoringDebtorId;

    @Column("factoring_agreement_id")
    private Long factoringAgreementId; // FK to FactoringAgreement

    @Column("debtor_customer_id")
    private Long debtorCustomerId;     // External reference for the debtor

    @Column("debtor_name")
    private String debtorName;

    @Column("debtor_credit_limit")
    private BigDecimal debtorCreditLimit;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
