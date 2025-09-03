package com.firefly.core.lending.factoring.web.controllers.invoice.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.services.invoice.v1.FactoringInvoiceSettlementService;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceSettlementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/invoices/{factoringInvoiceId}/settlements")
@Tag(name = "FactoringInvoiceSettlement", description = "Settlements for a factoring invoice")
@RequiredArgsConstructor
public class FactoringInvoiceSettlementController {

    private final FactoringInvoiceSettlementService service;

    @GetMapping
    @Operation(summary = "List or search settlements for a factoring invoice")
    public Mono<ResponseEntity<PaginationResponse<FactoringInvoiceSettlementDTO>>> findAll(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @ModelAttribute FilterRequest<FactoringInvoiceSettlementDTO> filterRequest) {

        return service.findAll(factoringAgreementId, factoringInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new settlement record")
    public Mono<ResponseEntity<FactoringInvoiceSettlementDTO>> create(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @Valid @RequestBody FactoringInvoiceSettlementDTO dto) {

        return service.create(factoringAgreementId, factoringInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringInvoiceSettlementId}")
    @Operation(summary = "Get a settlement record by ID")
    public Mono<ResponseEntity<FactoringInvoiceSettlementDTO>> getById(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @PathVariable UUID factoringInvoiceSettlementId) {

        return service.getById(factoringAgreementId, factoringInvoiceId, factoringInvoiceSettlementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringInvoiceSettlementId}")
    @Operation(summary = "Update a settlement record")
    public Mono<ResponseEntity<FactoringInvoiceSettlementDTO>> update(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @PathVariable UUID factoringInvoiceSettlementId,
            @Valid @RequestBody FactoringInvoiceSettlementDTO dto) {

        return service.update(factoringAgreementId, factoringInvoiceId, factoringInvoiceSettlementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringInvoiceSettlementId}")
    @Operation(summary = "Delete a settlement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringInvoiceId,
            @PathVariable UUID factoringInvoiceSettlementId) {

        return service.delete(factoringAgreementId, factoringInvoiceId, factoringInvoiceSettlementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}