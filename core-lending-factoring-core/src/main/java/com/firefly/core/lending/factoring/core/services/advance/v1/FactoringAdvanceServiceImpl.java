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
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.advance.v1.FactoringAdvanceMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.advance.v1.FactoringAdvanceDTO;
import com.firefly.core.lending.factoring.models.entities.advance.v1.FactoringAdvance;
import com.firefly.core.lending.factoring.models.repositories.advance.v1.FactoringAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringAdvanceServiceImpl implements FactoringAdvanceService {

    @Autowired
    private FactoringAdvanceRepository repository;

    @Autowired
    private FactoringAdvanceMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringAdvanceDTO>> findAll(UUID factoringAgreementId, UUID factoringInvoiceId, FilterRequest<FactoringAdvanceDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringAdvance.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringAdvanceDTO> create(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringAdvanceDTO dto) {
        FactoringAdvance entity = mapper.toEntity(dto);
        entity.setFactoringInvoiceId(factoringInvoiceId);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAdvanceDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringAdvanceId) {
        return repository.findById(factoringAdvanceId)
                .filter(advance -> factoringInvoiceId.equals(advance.getFactoringInvoiceId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAdvanceDTO> update(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringAdvanceId, FactoringAdvanceDTO dto) {
        return repository.findById(factoringAdvanceId)
                .filter(advance -> factoringInvoiceId.equals(advance.getFactoringInvoiceId()))
                .flatMap(existingAdvance -> {
                    FactoringAdvance updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setFactoringAdvanceId(existingAdvance.getFactoringAdvanceId());
                    updatedEntity.setFactoringInvoiceId(existingAdvance.getFactoringInvoiceId());
                    updatedEntity.setCreatedAt(existingAdvance.getCreatedAt());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringAdvanceId) {
        return repository.findById(factoringAdvanceId)
                .filter(advance -> factoringInvoiceId.equals(advance.getFactoringInvoiceId()))
                .flatMap(repository::delete);
    }
}