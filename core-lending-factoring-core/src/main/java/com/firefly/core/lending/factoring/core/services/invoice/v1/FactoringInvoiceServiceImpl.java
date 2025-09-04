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
import com.firefly.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoice;
import com.firefly.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringInvoiceServiceImpl implements FactoringInvoiceService {

    @Autowired
    private FactoringInvoiceRepository repository;

    @Autowired
    private FactoringInvoiceMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringInvoiceDTO>> findAll(UUID factoringAgreementId, FilterRequest<FactoringInvoiceDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringInvoice.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceDTO> create(UUID factoringAgreementId, FactoringInvoiceDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringInvoice entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .doOnNext(invoice -> invoice.setFactoringInvoiceId(null))
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId) {
        return repository.findById(factoringInvoiceId)
                .filter(invoice -> factoringAgreementId.equals(invoice.getFactoringAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceDTO> update(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringInvoiceDTO dto) {
        return repository.findById(factoringInvoiceId)
                .filter(invoice -> factoringAgreementId.equals(invoice.getFactoringAgreementId()))
                .flatMap(existingInvoice -> {
                    FactoringInvoice updated = mapper.toEntity(dto);
                    updated.setFactoringInvoiceId(factoringInvoiceId);
                    updated.setFactoringAgreementId(factoringAgreementId);
                    return repository.save(updated);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId) {
        return repository.findById(factoringInvoiceId)
                .filter(invoice -> factoringAgreementId.equals(invoice.getFactoringAgreementId()))
                .flatMap(repository::delete);
    }
}