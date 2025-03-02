package com.catalis.core.lending.factoring.models.entities.fee.v1;

import com.catalis.core.lending.factoring.interfaces.enums.fee.v1.FeeTypeEnum;
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
@Table("factoring_fee")
public class FactoringFee {

    @Id
    @Column("factoring_fee_id")
    private Long factoringFeeId;

    @Column("factoring_agreement_id")
    private Long factoringAgreementId; // FK to FactoringAgreement

    @Column("fee_type")
    private FeeTypeEnum feeType;

    @Column("fee_rate")
    private BigDecimal feeRate;   // e.g., 0.02 for 2% discount rate

    @Column("min_fee")
    private BigDecimal minFee;

    @Column("max_fee")
    private BigDecimal maxFee;

    @Column("is_active")
    private Boolean isActive;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}