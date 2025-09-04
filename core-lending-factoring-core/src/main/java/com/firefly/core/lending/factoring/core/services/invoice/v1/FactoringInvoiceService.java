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
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import reactor.core.publisher.Mono;

public interface FactoringInvoiceService {

    /**
     * Retrieves a paginated list of factoring invoices associated with a specific factoring agreement
     * based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement for which to retrieve invoices
     * @param filterRequest a FilterRequest object containing filter criteria and pagination settings for fetching invoices
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringInvoiceDTO objects
     */
    Mono<PaginationResponse<FactoringInvoiceDTO>> findAll(UUID factoringAgreementId,
                                                          FilterRequest<FactoringInvoiceDTO> filterRequest);

    /**
     * Creates a new factoring invoice associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement under which the invoice is created
     * @param dto the FactoringInvoiceDTO object containing the details of the invoice to be created
     * @return a Mono emitting the created FactoringInvoiceDTO object upon successful creation
     */
    Mono<FactoringInvoiceDTO> create(UUID factoringAgreementId, FactoringInvoiceDTO dto);

    /**
     * Retrieves a specific factoring invoice associated with the given factoring agreement
     * and factoring invoice identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to retrieve
     * @return a Mono emitting the FactoringInvoiceDTO if found, or an empty Mono if not
     */
    Mono<FactoringInvoiceDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId);

    /**
     * Updates an existing factoring invoice associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice is linked
     * @param factoringInvoiceId the unique identifier of the factoring invoice to be updated
     * @param dto the FactoringInvoiceDTO object containing the updated details of the factoring invoice
     * @return a Mono emitting the updated FactoringInvoiceDTO upon successful update
     */
    Mono<FactoringInvoiceDTO> update(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringInvoiceDTO dto);

    /**
     * Deletes a factoring invoice associated with the specified factoring agreement and
     * factoring invoice identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId);
}
