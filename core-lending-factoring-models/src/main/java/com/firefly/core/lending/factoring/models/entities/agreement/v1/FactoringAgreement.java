package com.firefly.core.lending.factoring.models.entities.agreement.v1;

import com.firefly.core.lending.factoring.interfaces.enums.agreement.v1.AgreementStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("factoring_agreement")
public class FactoringAgreement {

    @Id
    @Column("factoring_agreement_id")
    private UUID factoringAgreementId;

    @Column("contract_id")
    private UUID contractId;      // External reference

    @Column("customer_id")
    private UUID customerId;      // The factoring client (no direct FK)

    @Column("agreement_status")
    private AgreementStatusEnum agreementStatus;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("credit_limit")
    private BigDecimal creditLimit;

    @Column("recourse")
    private Boolean recourse;     // true = recourse factoring

    @Column("advance_rate")
    private BigDecimal advanceRate;  // e.g., 80.00 means 80%

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
