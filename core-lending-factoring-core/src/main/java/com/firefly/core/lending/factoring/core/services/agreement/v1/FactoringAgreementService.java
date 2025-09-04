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


package com.firefly.core.lending.factoring.core.services.agreement.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import reactor.core.publisher.Mono;

public interface FactoringAgreementService {

    /**
     * Retrieves a paginated list of factoring agreements based on the provided filter criteria.
     *
     * @param filterRequest contains filter criteria and pagination settings for fetching factoring agreements
     * @return a {@code Mono} wrapping a {@code PaginationResponse} that contains the list of {@code FactoringAgreementDTO} objects
     */
    Mono<PaginationResponse<FactoringAgreementDTO>> findAll(FilterRequest<FactoringAgreementDTO> filterRequest);

    /**
     * Creates and persists a new factoring agreement based on the provided information.
     *
     * @param dto the FactoringAgreementDTO object containing the details of the factoring agreement to create
     * @return a Mono emitting the created FactoringAgreementDTO object
     */
    Mono<FactoringAgreementDTO> create(FactoringAgreementDTO dto);

    /**
     * Retrieves a FactoringAgreementDTO by its unique identifier.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to retrieve
     * @return a Mono emitting the FactoringAgreementDTO if found, or an empty Mono if not
     */
    Mono<FactoringAgreementDTO> getById(UUID factoringAgreementId);

    /**
     * Updates an existing factoring agreement with the provided details.
     *
     * @param factoringAgreementId the identifier of the factoring agreement to be updated
     * @param dto the data transfer object containing updated details of the factoring agreement
     * @return a Mono emitting the updated factoring agreement DTO upon successful update
     */
    Mono<FactoringAgreementDTO> update(UUID factoringAgreementId, FactoringAgreementDTO dto);

    /**
     * Deletes a factoring agreement by its unique identifier.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to delete
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId);

}