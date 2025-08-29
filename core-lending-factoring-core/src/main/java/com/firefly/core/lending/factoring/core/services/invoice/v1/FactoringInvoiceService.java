package com.firefly.core.lending.factoring.core.services.invoice.v1;

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
    Mono<PaginationResponse<FactoringInvoiceDTO>> findAll(Long factoringAgreementId,
                                                          FilterRequest<FactoringInvoiceDTO> filterRequest);

    /**
     * Creates a new factoring invoice associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement under which the invoice is created
     * @param dto the FactoringInvoiceDTO object containing the details of the invoice to be created
     * @return a Mono emitting the created FactoringInvoiceDTO object upon successful creation
     */
    Mono<FactoringInvoiceDTO> create(Long factoringAgreementId, FactoringInvoiceDTO dto);

    /**
     * Retrieves a specific factoring invoice associated with the given factoring agreement
     * and factoring invoice identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to retrieve
     * @return a Mono emitting the FactoringInvoiceDTO if found, or an empty Mono if not
     */
    Mono<FactoringInvoiceDTO> getById(Long factoringAgreementId, Long factoringInvoiceId);

    /**
     * Updates an existing factoring invoice associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice is linked
     * @param factoringInvoiceId the unique identifier of the factoring invoice to be updated
     * @param dto the FactoringInvoiceDTO object containing the updated details of the factoring invoice
     * @return a Mono emitting the updated FactoringInvoiceDTO upon successful update
     */
    Mono<FactoringInvoiceDTO> update(Long factoringAgreementId, Long factoringInvoiceId, FactoringInvoiceDTO dto);

    /**
     * Deletes a factoring invoice associated with the specified factoring agreement and
     * factoring invoice identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(Long factoringAgreementId, Long factoringInvoiceId);
}
