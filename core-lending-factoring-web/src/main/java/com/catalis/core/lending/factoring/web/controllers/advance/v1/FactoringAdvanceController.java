package com.catalis.core.lending.factoring.web.controllers.advance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.services.advance.v1.FactoringAdvanceService;
import com.catalis.core.lending.factoring.interfaces.dtos.advance.v1.FactoringAdvanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/invoices/{factoringInvoiceId}/advances")
@Tag(name = "FactoringAdvance", description = "Advance records for an invoice under a factoring agreement")
@RequiredArgsConstructor
public class FactoringAdvanceController {

    private final FactoringAdvanceService service;

    @GetMapping
    @Operation(summary = "List or search advances for a factoring invoice")
    public Mono<ResponseEntity<PaginationResponse<FactoringAdvanceDTO>>> findAll(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @ModelAttribute FilterRequest<FactoringAdvanceDTO> filterRequest) {

        return service.findAll(factoringAgreementId, factoringInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new advance record")
    public Mono<ResponseEntity<FactoringAdvanceDTO>> create(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @RequestBody FactoringAdvanceDTO dto) {

        return service.create(factoringAgreementId, factoringInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringAdvanceId}")
    @Operation(summary = "Get an advance record by ID")
    public Mono<ResponseEntity<FactoringAdvanceDTO>> getById(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringAdvanceId) {

        return service.getById(factoringAgreementId, factoringInvoiceId, factoringAdvanceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringAdvanceId}")
    @Operation(summary = "Update an advance record")
    public Mono<ResponseEntity<FactoringAdvanceDTO>> update(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringAdvanceId,
            @RequestBody FactoringAdvanceDTO dto) {

        return service.update(factoringAgreementId, factoringInvoiceId, factoringAdvanceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringAdvanceId}")
    @Operation(summary = "Delete an advance record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringAdvanceId) {

        return service.delete(factoringAgreementId, factoringInvoiceId, factoringAdvanceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
