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


package com.firefly.core.lending.factoring.core.services.agreement.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.agreement.v1.FactoringAgreementMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import com.firefly.core.lending.factoring.models.entities.agreement.v1.FactoringAgreement;
import com.firefly.core.lending.factoring.models.repositories.agreement.v1.FactoringAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringAgreementServiceImpl implements FactoringAgreementService {

    @Autowired
    private FactoringAgreementRepository repository;

    @Autowired
    private FactoringAgreementMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringAgreementDTO>> findAll(FilterRequest<FactoringAgreementDTO> filterRequest) {
        return FilterUtils.createFilter(
                FactoringAgreement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringAgreementDTO> create(FactoringAgreementDTO dto) {
        FactoringAgreement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAgreementDTO> getById(UUID factoringAgreementId) {
        return repository.findById(factoringAgreementId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAgreementDTO> update(UUID factoringAgreementId, FactoringAgreementDTO dto) {
        return repository.findById(factoringAgreementId)
                .flatMap(existing -> {
                    FactoringAgreement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setFactoringAgreementId(factoringAgreementId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId) {
        return repository.deleteById(factoringAgreementId);
    }
}