/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.factoring.models.entities.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.firefly.core.lending.factoring.interfaces.enums.invoice.v1.InvoiceStatusEnum;
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
@Table("factoring_invoice")
public class FactoringInvoice {

    @Id
    @Column("factoring_invoice_id")
    private UUID factoringInvoiceId;

    @Column("factoring_agreement_id")
    private UUID factoringAgreementId; // FK to FactoringAgreement

    @Column("factoring_debtor_id")
    private UUID factoringDebtorId;    // Optional FK to FactoringDebtor

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
    private UUID documentReference;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}