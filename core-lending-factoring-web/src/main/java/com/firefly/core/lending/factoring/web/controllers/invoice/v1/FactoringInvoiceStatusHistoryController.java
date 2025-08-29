package com.firefly.core.lending.factoring.web.controllers.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.services.invoice.v1.FactoringInvoiceStatusHistoryService;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/invoices/{factoringInvoiceId}/status-history")
@Tag(name = "FactoringInvoiceStatusHistory", description = "Status history of factoring invoices")
@RequiredArgsConstructor
public class FactoringInvoiceStatusHistoryController {

    private final FactoringInvoiceStatusHistoryService service;

    @GetMapping
    @Operation(summary = "List or search invoice status history entries")
    public Mono<ResponseEntity<PaginationResponse<FactoringInvoiceStatusHistoryDTO>>> findAll(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @ModelAttribute FilterRequest<FactoringInvoiceStatusHistoryDTO> filterRequest) {

        return service.findAll(factoringAgreementId, factoringInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new invoice status history entry")
    public Mono<ResponseEntity<FactoringInvoiceStatusHistoryDTO>> create(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @RequestBody FactoringInvoiceStatusHistoryDTO dto) {

        return service.create(factoringAgreementId, factoringInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringInvoiceStatusHistoryId}")
    @Operation(summary = "Get an invoice status history entry by ID")
    public Mono<ResponseEntity<FactoringInvoiceStatusHistoryDTO>> getById(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringInvoiceStatusHistoryId) {

        return service.getById(factoringAgreementId, factoringInvoiceId, factoringInvoiceStatusHistoryId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringInvoiceStatusHistoryId}")
    @Operation(summary = "Update an invoice status history entry")
    public Mono<ResponseEntity<FactoringInvoiceStatusHistoryDTO>> update(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringInvoiceStatusHistoryId,
            @RequestBody FactoringInvoiceStatusHistoryDTO dto) {

        return service.update(factoringAgreementId, factoringInvoiceId, factoringInvoiceStatusHistoryId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringInvoiceStatusHistoryId}")
    @Operation(summary = "Delete an invoice status history entry")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringInvoiceId,
            @PathVariable Long factoringInvoiceStatusHistoryId) {

        return service.delete(factoringAgreementId, factoringInvoiceId, factoringInvoiceStatusHistoryId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}