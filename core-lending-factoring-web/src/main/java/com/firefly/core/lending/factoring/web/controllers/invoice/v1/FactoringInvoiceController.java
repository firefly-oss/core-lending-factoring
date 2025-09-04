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


package com.firefly.core.lending.factoring.web.controllers.invoice.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.services.invoice.v1.FactoringInvoiceService;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/invoices")
@Tag(name = "FactoringInvoice", description = "Invoice operations under a factoring agreement")
@RequiredArgsConstructor
public class FactoringInvoiceController {

    private final FactoringInvoiceService service;

    @GetMapping
    @Operation(summary = "List or search invoices under a factoring agreement")
    public Mono<ResponseEntity<PaginationResponse<FactoringInvoiceDTO>>> findAll(
            @PathVariable UUID factoringAgreementId,
            @ModelAttribute FilterRequest<FactoringInvoiceDTO> filterRequest) {

        return service.findAll(factoringAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new factoring invoice")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> create(
            @PathVariable UUID factoringAgreementId,
            @Valid @RequestBody FactoringInvoiceDTO dto) {

        return service.create(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringInvoiceId}")
    @Operation(summary = "Get an invoice by ID")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> getById(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId) {

        return service.getById(factoringAgreementId, factoringInvoiceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringInvoiceId}")
    @Operation(summary = "Update an invoice record")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> update(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @Valid @RequestBody FactoringInvoiceDTO dto) {

        return service.update(factoringAgreementId, factoringInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringInvoiceId}")
    @Operation(summary = "Delete an invoice record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId) {

        return service.delete(factoringAgreementId, factoringInvoiceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
