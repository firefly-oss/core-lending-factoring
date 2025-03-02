package com.catalis.core.lending.factoring.web.controllers.debtor.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.services.debtor.v1.FactoringDebtorService;
import com.catalis.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/debtors")
@Tag(name = "FactoringDebtor", description = "Debtor operations under a factoring agreement")
@RequiredArgsConstructor
public class FactoringDebtorController {

    private final FactoringDebtorService service;

    @GetMapping
    @Operation(summary = "List or search debtors for a factoring agreement")
    public Mono<ResponseEntity<PaginationResponse<FactoringDebtorDTO>>> findAll(
            @PathVariable Long factoringAgreementId,
            @ModelAttribute FilterRequest<FactoringDebtorDTO> filterRequest) {

        return service.findAll(factoringAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new debtor record")
    public Mono<ResponseEntity<FactoringDebtorDTO>> create(
            @PathVariable Long factoringAgreementId,
            @RequestBody FactoringDebtorDTO dto) {

        return service.create(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringDebtorId}")
    @Operation(summary = "Get a debtor record by ID")
    public Mono<ResponseEntity<FactoringDebtorDTO>> getById(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringDebtorId) {

        return service.getById(factoringAgreementId, factoringDebtorId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringDebtorId}")
    @Operation(summary = "Update a debtor record")
    public Mono<ResponseEntity<FactoringDebtorDTO>> update(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringDebtorId,
            @RequestBody FactoringDebtorDTO dto) {

        return service.update(factoringAgreementId, factoringDebtorId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringDebtorId}")
    @Operation(summary = "Delete a debtor record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringDebtorId) {

        return service.delete(factoringAgreementId, factoringDebtorId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
