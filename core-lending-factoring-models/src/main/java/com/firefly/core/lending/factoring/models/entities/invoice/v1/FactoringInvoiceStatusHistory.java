package com.firefly.core.lending.factoring.models.entities.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("factoring_invoice_status_history")
public class FactoringInvoiceStatusHistory {

    @Id
    @Column("factoring_invoice_status_history_id")
    private Long factoringInvoiceStatusHistoryId;

    @Column("factoring_invoice_id")
    private Long factoringInvoiceId; // FK to FactoringInvoice

    @Column("old_status")
    private InvoiceStatusEnum oldStatus;

    @Column("new_status")
    private InvoiceStatusEnum newStatus;

    @Column("changed_at")
    private LocalDateTime changedAt;

    @Column("changed_by")
    private String changedBy;

    @Column("reason")
    private String reason;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
