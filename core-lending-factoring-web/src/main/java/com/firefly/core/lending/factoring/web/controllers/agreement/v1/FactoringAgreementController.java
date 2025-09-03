package com.firefly.core.lending.factoring.web.controllers.agreement.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.services.agreement.v1.FactoringAgreementService;
import com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

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
    public Mono<ResponseEntity<FactoringAgreementDTO>> create(@Valid @RequestBody FactoringAgreementDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringAgreementId}")
    @Operation(summary = "Get a factoring agreement by ID")
    public Mono<ResponseEntity<FactoringAgreementDTO>> getById(@PathVariable UUID factoringAgreementId) {
        return service.getById(factoringAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringAgreementId}")
    @Operation(summary = "Update a factoring agreement")
    public Mono<ResponseEntity<FactoringAgreementDTO>> update(
            @PathVariable UUID factoringAgreementId,
            @Valid @RequestBody FactoringAgreementDTO dto) {
        return service.update(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringAgreementId}")
    @Operation(summary = "Delete a factoring agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID factoringAgreementId) {
        return service.delete(factoringAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}