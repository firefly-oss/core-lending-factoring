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


package com.firefly.core.lending.factoring.core.services.fee.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.fee.v1.FactoringFeeMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import com.firefly.core.lending.factoring.models.entities.fee.v1.FactoringFee;
import com.firefly.core.lending.factoring.models.repositories.fee.v1.FactoringFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class FactoringFeeServiceImpl implements FactoringFeeService {

    @Autowired
    private FactoringFeeRepository repository;

    @Autowired
    private FactoringFeeMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringFeeDTO>> findAll(UUID factoringAgreementId, FilterRequest<FactoringFeeDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringFee.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringFeeDTO> create(UUID factoringAgreementId, FactoringFeeDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringFee entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringFeeDTO> getById(UUID factoringAgreementId, UUID factoringFeeId) {
        return repository.findById(factoringFeeId)
                .filter(fee -> fee.getFactoringAgreementId().equals(factoringAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringFeeDTO> update(UUID factoringAgreementId, UUID factoringFeeId, FactoringFeeDTO dto) {
        return repository.findById(factoringFeeId)
                .filter(fee -> fee.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(existingFee -> {
                    FactoringFee updatedFee = mapper.toEntity(dto);
                    updatedFee.setFactoringFeeId(factoringFeeId);
                    updatedFee.setFactoringAgreementId(factoringAgreementId);
                    updatedFee.setCreatedAt(existingFee.getCreatedAt());
                    updatedFee.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedFee);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringFeeId) {
        return repository.findById(factoringFeeId)
                .filter(fee -> fee.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(repository::delete);
    }
}