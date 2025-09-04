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
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.debtor.v1.FactoringDebtorMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import com.firefly.core.lending.factoring.models.entities.debtor.v1.FactoringDebtor;
import com.firefly.core.lending.factoring.models.repositories.debtor.v1.FactoringDebtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringDebtorServiceImpl implements FactoringDebtorService {

    @Autowired
    private FactoringDebtorRepository repository;

    @Autowired
    private FactoringDebtorMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringDebtorDTO>> findAll(UUID factoringAgreementId, FilterRequest<FactoringDebtorDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringDebtor.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringDebtorDTO> create(UUID factoringAgreementId, FactoringDebtorDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringDebtor entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringDebtorDTO> getById(UUID factoringAgreementId, UUID factoringDebtorId) {
        return repository.findById(factoringDebtorId)
                .filter(entity -> entity.getFactoringAgreementId().equals(factoringAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringDebtorDTO> update(UUID factoringAgreementId, UUID factoringDebtorId, FactoringDebtorDTO dto) {
        return repository.findById(factoringDebtorId)
                .filter(entity -> entity.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(entity -> {
                    dto.setFactoringDebtorId(factoringDebtorId);
                    dto.setFactoringAgreementId(factoringAgreementId);
                    FactoringDebtor updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringDebtorId) {
        return repository.findById(factoringDebtorId)
                .filter(entity -> entity.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(repository::delete);
    }
}