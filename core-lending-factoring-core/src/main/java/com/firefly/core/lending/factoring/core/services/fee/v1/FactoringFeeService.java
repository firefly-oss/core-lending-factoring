package com.firefly.core.lending.factoring.core.services.fee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import reactor.core.publisher.Mono;

public interface FactoringFeeService {

    /**
     * Retrieves a paginated list of factoring fees associated with a specific factoring agreement
     * based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement for which to retrieve fees
     * @param filterRequest a FilterRequest object containing filter criteria and pagination settings for fetching fees
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringFeeDTO objects
     */
    Mono<PaginationResponse<FactoringFeeDTO>> findAll(Long factoringAgreementId,
                                                      FilterRequest<FactoringFeeDTO> filterRequest);

    /**
     * Creates a new factoring fee associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement under which the fee is created
     * @param dto the data transfer object containing the details of the factoring fee to be created
     * @return a Mono emitting the created FactoringFeeDTO object upon successful creation
     */
    Mono<FactoringFeeDTO> create(Long factoringAgreementId, FactoringFeeDTO dto);

    /**
     * Retrieves a specific factoring fee associated with the given factoring agreement and fee identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the fee belongs
     * @param factoringFeeId the unique identifier of the factoring fee to retrieve
     * @return a Mono emitting the FactoringFeeDTO if found, or an empty Mono if not
     */
    Mono<FactoringFeeDTO> getById(Long factoringAgreementId, Long factoringFeeId);

    /**
     * Updates an existing factoring fee associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the fee is linked
     * @param factoringFeeId the unique identifier of the factoring fee to be updated
     * @param dto the FactoringFeeDTO object containing the updated details of the factoring fee
     * @return a Mono emitting the updated FactoringFeeDTO upon successful update
     */
    Mono<FactoringFeeDTO> update(Long factoringAgreementId, Long factoringFeeId, FactoringFeeDTO dto);

    /**
     * Deletes a factoring fee associated with the specified factoring agreement and factoring fee identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the fee belongs
     * @param factoringFeeId the unique identifier of the factoring fee to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(Long factoringAgreementId, Long factoringFeeId);
}
