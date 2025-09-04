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


package com.firefly.core.lending.factoring.core.services.advance.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.advance.v1.FactoringAdvanceDTO;
import reactor.core.publisher.Mono;

public interface FactoringAdvanceService {

    /**
     * Retrieves a paginated list of factoring advances associated with a specific factoring agreement and invoice
     * based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement for which to retrieve advances
     * @param factoringInvoiceId the unique identifier of the factoring invoice for which to retrieve advances
     * @param filterRequest a FilterRequest object containing filter criteria and pagination settings for fetching advances
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringAdvanceDTO objects
     */
    Mono<PaginationResponse<FactoringAdvanceDTO>> findAll(UUID factoringAgreementId,
                                                          UUID factoringInvoiceId,
                                                          FilterRequest<FactoringAdvanceDTO> filterRequest);

    /**
     * Creates a new factoring advance associated with a specific factoring agreement and factoring invoice.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the advance is associated
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the advance relates
     * @param dto the data transfer object containing the details of the factoring advance to be created
     * @return a Mono emitting the created FactoringAdvanceDTO object upon successful creation
     */
    Mono<FactoringAdvanceDTO> create(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringAdvanceDTO dto);

    /**
     * Retrieves a specific factoring advance associated with the given factoring agreement, invoice, and advance identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the advance belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the advance is linked
     * @param factoringAdvanceId the unique identifier of the factoring advance to be retrieved
     * @return a Mono emitting the FactoringAdvanceDTO if found, or an empty Mono if not
     */
    Mono<FactoringAdvanceDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringAdvanceId);

    /**
     * Updates an existing factoring advance associated with a specific factoring agreement
     * and factoring invoice, using the provided identifiers and updated details.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the advance is linked
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the advance belongs
     * @param factoringAdvanceId the unique identifier of the factoring advance to be updated
     * @param dto the FactoringAdvanceDTO object containing the updated details of the factoring advance
     * @return a Mono emitting the updated FactoringAdvanceDTO upon successful update
     */
    Mono<FactoringAdvanceDTO> update(UUID factoringAgreementId,
                                     UUID factoringInvoiceId,
                                     UUID factoringAdvanceId,
                                     FactoringAdvanceDTO dto);

    /**
     * Deletes a factoring advance associated with the specified factoring agreement, invoice, and advance identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the advance belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the advance is linked
     * @param factoringAdvanceId the unique identifier of the factoring advance to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringAdvanceId);
}