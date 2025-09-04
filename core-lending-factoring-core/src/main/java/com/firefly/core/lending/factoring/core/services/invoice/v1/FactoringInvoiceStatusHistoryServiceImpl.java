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
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceStatusHistoryMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import com.firefly.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringInvoiceStatusHistoryServiceImpl implements FactoringInvoiceStatusHistoryService {

    @Autowired
    private FactoringInvoiceStatusHistoryRepository repository;

    @Autowired
    private FactoringInvoiceStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringInvoiceStatusHistoryDTO>> findAll(UUID factoringAgreementId, UUID factoringInvoiceId, FilterRequest<FactoringInvoiceStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringInvoiceStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> create(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringInvoiceStatusHistoryDTO dto) {
        FactoringInvoiceStatusHistory entity = mapper.toEntity(dto);
        entity.setFactoringInvoiceId(factoringInvoiceId);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> update(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId, FactoringInvoiceStatusHistoryDTO dto) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .flatMap(existing -> {
                    if (!existing.getFactoringInvoiceId().equals(factoringInvoiceId)) {
                        return Mono.empty();
                    }
                    FactoringInvoiceStatusHistory updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setFactoringInvoiceStatusHistoryId(factoringInvoiceStatusHistoryId);
                    updatedEntity.setFactoringInvoiceId(factoringInvoiceId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .flatMap(repository::delete);
    }
}