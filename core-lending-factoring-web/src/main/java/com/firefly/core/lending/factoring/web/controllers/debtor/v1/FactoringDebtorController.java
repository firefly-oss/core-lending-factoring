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


package com.firefly.core.lending.factoring.web.controllers.debtor.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.services.debtor.v1.FactoringDebtorService;
import com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/factoring-agreements/{factoringAgreementId}/debtors")
@Tag(name = "FactoringDebtor", description = "Debtor operations under a factoring agreement")
@RequiredArgsConstructor
public class FactoringDebtorController {

    private final FactoringDebtorService service;

    @GetMapping
    @Operation(summary = "List or search debtors for a factoring agreement")
    public Mono<ResponseEntity<PaginationResponse<FactoringDebtorDTO>>> findAll(
            @PathVariable UUID factoringAgreementId,
            @ModelAttribute FilterRequest<FactoringDebtorDTO> filterRequest) {

        return service.findAll(factoringAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new debtor record")
    public Mono<ResponseEntity<FactoringDebtorDTO>> create(
            @PathVariable UUID factoringAgreementId,
            @Valid @RequestBody FactoringDebtorDTO dto) {

        return service.create(factoringAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{factoringDebtorId}")
    @Operation(summary = "Get a debtor record by ID")
    public Mono<ResponseEntity<FactoringDebtorDTO>> getById(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringDebtorId) {

        return service.getById(factoringAgreementId, factoringDebtorId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{factoringDebtorId}")
    @Operation(summary = "Update a debtor record")
    public Mono<ResponseEntity<FactoringDebtorDTO>> update(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringDebtorId,
            @Valid @RequestBody FactoringDebtorDTO dto) {

        return service.update(factoringAgreementId, factoringDebtorId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{factoringDebtorId}")
    @Operation(summary = "Delete a debtor record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID factoringAgreementId,
            @PathVariable UUID factoringDebtorId) {

        return service.delete(factoringAgreementId, factoringDebtorId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
