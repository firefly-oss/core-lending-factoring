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


package com.firefly.core.lending.factoring.core.services.invoice.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import reactor.core.publisher.Mono;

public interface FactoringInvoiceStatusHistoryService {

    /**
     * Retrieves a paginated list of factoring invoice status history records associated with
     * a specific factoring agreement and factoring invoice, based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement associated with the invoice status history
     * @param factoringInvoiceId the unique identifier of the factoring invoice for which the status history is retrieved
     * @param filterRequest a FilterRequest object containing filter criteria and pagination settings for fetching status history records
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringInvoiceStatusHistoryDTO objects
     */
    Mono<PaginationResponse<FactoringInvoiceStatusHistoryDTO>> findAll(UUID factoringAgreementId,
                                                                       UUID factoringInvoiceId,
                                                                       FilterRequest<FactoringInvoiceStatusHistoryDTO> filterRequest);

    /**
     * Creates a new record in the factoring invoice status history associated with a specific factoring agreement
     * and factoring invoice.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement under which the status history is to be created
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the status history entry will be linked
     * @param dto the data transfer object containing the details of the status history to be created
     * @return a Mono emitting the created FactoringInvoiceStatusHistoryDTO object upon successful creation
     */
    Mono<FactoringInvoiceStatusHistoryDTO> create(UUID factoringAgreementId,
                                                  UUID factoringInvoiceId,
                                                  FactoringInvoiceStatusHistoryDTO dto);

    /**
     * Retrieves a specific factoring invoice status history record associated with the specified
     * factoring agreement, factoring invoice, and factoring invoice status history identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the
     *                              invoice belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the status
     *                           history is associated
     * @param factoringInvoiceStatusHistoryId the unique identifier of the factoring invoice status
     *                                         history record to retrieve
     * @return a Mono emitting the FactoringInvoiceStatusHistoryDTO if found, or an empty Mono if not
     */
    Mono<FactoringInvoiceStatusHistoryDTO> getById(UUID factoringAgreementId,
                                                   UUID factoringInvoiceId,
                                                   UUID factoringInvoiceStatusHistoryId);

    /**
     * Updates an existing factoring invoice status history record associated with the specified identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement associated with the invoice status history
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the status history belongs
     * @param factoringInvoiceStatusHistoryId the unique identifier of the factoring invoice status history record to update
     * @param dto a FactoringInvoiceStatusHistoryDTO object containing the updated details of the invoice status history
     * @return a Mono emitting the updated FactoringInvoiceStatusHistoryDTO upon successful update
     */
    Mono<FactoringInvoiceStatusHistoryDTO> update(UUID factoringAgreementId,
                                                  UUID factoringInvoiceId,
                                                  UUID factoringInvoiceStatusHistoryId,
                                                  FactoringInvoiceStatusHistoryDTO dto);

    /**
     * Deletes a factoring invoice status history associated with the specified factoring agreement,
     * factoring invoice, and factoring invoice status history identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice status history belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the status history belongs
     * @param factoringInvoiceStatusHistoryId the unique identifier of the factoring invoice status history to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId);
}