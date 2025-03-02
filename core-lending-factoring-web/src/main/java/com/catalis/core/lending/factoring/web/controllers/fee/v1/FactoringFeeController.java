package com.catalis.core.lending.factoring.web.controllers.fee.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.services.fee.v1.FactoringFeeService;
import com.catalis.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/fees")
@Tag(name = "FactoringFee", description = "Fee configuration under a factoring agreement")
@RequiredArgsConstructor
public class FactoringFeeController {

    private final FactoringFeeService service;

    @GetMapping
    @Operation(summary = "List or search fees for a factoring agreement")
    public Mono<ResponseEntity<PaginationResponse<FactoringFeeDTO>>> findAll(
            @PathVariable Long factoringAgreementId,
            @ModelAttribute FilterRequest<FactoringFeeDTO> filterRequest) {

        return service.findAll(factoringAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new fee configuration")
    public Mono<ResponseEntity<FactoringFeeDTO>> create(
            @PathVariable Long factoringAgreementId,
            @RequestBody FactoringFeeDTO dto) {

        return service.create(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringFeeId}")
    @Operation(summary = "Get a fee configuration by ID")
    public Mono<ResponseEntity<FactoringFeeDTO>> getById(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringFeeId) {

        return service.getById(factoringAgreementId, factoringFeeId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringFeeId}")
    @Operation(summary = "Update a fee configuration")
    public Mono<ResponseEntity<FactoringFeeDTO>> update(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringFeeId,
            @RequestBody FactoringFeeDTO dto) {

        return service.update(factoringAgreementId, factoringFeeId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringFeeId}")
    @Operation(summary = "Delete a fee configuration")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long factoringAgreementId,
            @PathVariable Long factoringFeeId) {

        return service.delete(factoringAgreementId, factoringFeeId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
