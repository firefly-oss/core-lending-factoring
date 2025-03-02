package com.catalis.core.lending.factoring.web.controllers.agreement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.services.agreement.v1.FactoringAgreementService;
import com.catalis.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements")
@Tag(name = "FactoringAgreement", description = "Operations on factoring agreements")
@RequiredArgsConstructor
public class FactoringAgreementController {

    private final FactoringAgreementService service;

    @GetMapping
    @Operation(summary = "List or search factoring agreements")
    public Mono<ResponseEntity<PaginationResponse<FactoringAgreementDTO>>> findAll(
            @ModelAttribute FilterRequest<FactoringAgreementDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new factoring agreement")
    public Mono<ResponseEntity<FactoringAgreementDTO>> create(@RequestBody FactoringAgreementDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringAgreementId}")
    @Operation(summary = "Get a factoring agreement by ID")
    public Mono<ResponseEntity<FactoringAgreementDTO>> getById(@PathVariable Long factoringAgreementId) {
        return service.getById(factoringAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringAgreementId}")
    @Operation(summary = "Update a factoring agreement")
    public Mono<ResponseEntity<FactoringAgreementDTO>> update(
            @PathVariable Long factoringAgreementId,
            @RequestBody FactoringAgreementDTO dto) {
        return service.update(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringAgreementId}")
    @Operation(summary = "Delete a factoring agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long factoringAgreementId) {
        return service.delete(factoringAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}