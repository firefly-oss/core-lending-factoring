package com.catalis.core.lending.factoring.web.controllers.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.services.invoice.v1.FactoringInvoiceService;
import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/invoices")
@Tag(name = "FactoringInvoice", description = "Invoice operations under a factoring agreement")
@RequiredArgsConstructor
public class FactoringInvoiceController {

    private final FactoringInvoiceService service;

    @GetMapping
    @Operation(summary = "List or search invoices under a factoring agreement")
    public Mono<ResponseEntity<PaginationResponse<FactoringInvoiceDTO>>> findAll(
            @PathVariable Long factoringAgreementId,
            @ModelAttribute FilterRequest<FactoringInvoiceDTO> filterRequest) {

        return service.findAll(factoringAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new factoring invoice")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> create(
            @PathVariable Long factoringAgreementId,
            @RequestBody FactoringInvoiceDTO dto) {

        return service.create(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringInvoiceId}")
    @Operation(summary = "Get an invoice by ID")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> getById(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId) {

        return service.getById(factoringAgreementId, factoringInvoiceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringInvoiceId}")
    @Operation(summary = "Update an invoice record")
    public Mono<ResponseEntity<FactoringInvoiceDTO>> update(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @RequestBody FactoringInvoiceDTO dto) {

        return service.update(factoringAgreementId, factoringInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringInvoiceId}")
    @Operation(summary = "Delete an invoice record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId) {

        return service.delete(factoringAgreementId, factoringInvoiceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
