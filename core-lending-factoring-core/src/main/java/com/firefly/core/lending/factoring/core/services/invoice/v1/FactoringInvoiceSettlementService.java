package com.firefly.core.lending.factoring.core.services.invoice.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceSettlementDTO;
import reactor.core.publisher.Mono;

public interface FactoringInvoiceSettlementService {

    /**
     * Retrieves a paginated list of factoring invoice settlements associated with a specific factoring agreement
     * and factoring invoice, based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the settlements belong
     * @param factoringInvoiceId the unique identifier of the factoring invoice for which to retrieve settlements
     * @param filterRequest a FilterRequest object containing filter criteria and pagination settings for fetching settlements
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringInvoiceSettlementDTO objects
     */
    Mono<PaginationResponse<FactoringInvoiceSettlementDTO>> findAll(UUID factoringAgreementId,
                                                                    UUID factoringInvoiceId,
                                                                    FilterRequest<FactoringInvoiceSettlementDTO> filterRequest);

    /**
     * Creates a new factoring invoice settlement within a specific factoring agreement and invoice.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the settlement is linked
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the settlement applies
     * @param dto the data transfer object containing the details of the factoring invoice settlement to be created
     * @return a Mono emitting the created FactoringInvoiceSettlementDTO object upon successful creation
     */
    Mono<FactoringInvoiceSettlementDTO> create(UUID factoringAgreementId,
                                               UUID factoringInvoiceId,
                                               FactoringInvoiceSettlementDTO dto);

    /**
     * Retrieves a specific factoring invoice settlement based on the provided identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice settlement belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice associated with the settlement
     * @param factoringInvoiceSettlementId the unique identifier of the factoring invoice settlement to retrieve
     * @return a Mono emitting the FactoringInvoiceSettlementDTO if found, or an empty Mono if not
     */
    Mono<FactoringInvoiceSettlementDTO> getById(UUID factoringAgreementId,
                                                UUID factoringInvoiceId,
                                                UUID factoringInvoiceSettlementId);

    /**
     * Updates an existing factoring invoice settlement associated with a specific factoring agreement and factoring invoice.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the invoice settlement belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice which the settlement is linked to
     * @param factoringInvoiceSettlementId the unique identifier of the factoring invoice settlement to update
     * @param dto the FactoringInvoiceSettlementDTO containing the updated details of the settlement
     * @return a Mono emitting the updated FactoringInvoiceSettlementDTO upon successful update
     */
    Mono<FactoringInvoiceSettlementDTO> update(UUID factoringAgreementId,
                                               UUID factoringInvoiceId,
                                               UUID factoringInvoiceSettlementId,
                                               FactoringInvoiceSettlementDTO dto);

    /**
     * Deletes a factoring invoice settlement associated with the specified factoring agreement,
     * factoring invoice, and settlement identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the settlement belongs
     * @param factoringInvoiceId the unique identifier of the factoring invoice to which the settlement belongs
     * @param factoringInvoiceSettlementId the unique identifier of the factoring invoice settlement to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceSettlementId);
}