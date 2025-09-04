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


package com.firefly.core.lending.factoring.core.services.debtor.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import reactor.core.publisher.Mono;

public interface FactoringDebtorService {

    /**
     * Retrieves a paginated list of factoring debtors associated with a specific factoring agreement based on the provided filter criteria.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement for which to retrieve debtors
     * @param filterRequest a FilterRequest object that contains filter criteria and pagination settings for fetching debtors
     * @return a Mono emitting a PaginationResponse containing a list of matching FactoringDebtorDTO objects
     */
    Mono<PaginationResponse<FactoringDebtorDTO>> findAll(UUID factoringAgreementId,
                                                         FilterRequest<FactoringDebtorDTO> filterRequest);

    /**
     * Creates and persists a new factoring debtor associated with a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the debtor is linked
     * @param dto the data transfer object containing the details of the factoring debtor to be created
     * @return a Mono emitting the created FactoringDebtorDTO object upon successful creation
     */
    Mono<FactoringDebtorDTO> create(UUID factoringAgreementId, FactoringDebtorDTO dto);

    /**
     * Retrieves a FactoringDebtorDTO by its unique identifier within a specific factoring agreement.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the debtor belongs
     * @param factoringDebtorId the unique identifier of the factoring debtor to retrieve
     * @return a Mono emitting the FactoringDebtorDTO if found, or an empty Mono if not
     */
    Mono<FactoringDebtorDTO> getById(UUID factoringAgreementId, UUID factoringDebtorId);

    /**
     * Updates an existing factoring debtor record based on the provided identifiers and data transfer object.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement associated with the debtor
     * @param factoringDebtorId the unique identifier of the factoring debtor to update
     * @param dto the FactoringDebtorDTO object containing the updated details of the factoring debtor
     * @return a Mono emitting the updated FactoringDebtorDTO upon successful update
     */
    Mono<FactoringDebtorDTO> update(UUID factoringAgreementId, UUID factoringDebtorId, FactoringDebtorDTO dto);

    /**
     * Deletes a factoring debtor entity associated with the specified factoring agreement
     * and factoring debtor identifiers.
     *
     * @param factoringAgreementId the unique identifier of the factoring agreement to which the debtor belongs
     * @param factoringDebtorId the unique identifier of the factoring debtor to be deleted
     * @return a {@code Mono<Void>} signaling the completion of the delete operation
     */
    Mono<Void> delete(UUID factoringAgreementId, UUID factoringDebtorId);
}
