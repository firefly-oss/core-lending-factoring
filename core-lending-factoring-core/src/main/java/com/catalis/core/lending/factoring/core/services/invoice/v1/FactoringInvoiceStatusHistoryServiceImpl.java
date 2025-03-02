package com.catalis.core.lending.factoring.core.services.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceStatusHistoryMapper;
import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.catalis.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import com.catalis.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceStatusHistoryRepository;
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
    public Mono<PaginationResponse<FactoringInvoiceStatusHistoryDTO>> findAll(Long factoringAgreementId, Long factoringInvoiceId, FilterRequest<FactoringInvoiceStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringInvoiceStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> create(Long factoringAgreementId, Long factoringInvoiceId, FactoringInvoiceStatusHistoryDTO dto) {
        FactoringInvoiceStatusHistory entity = mapper.toEntity(dto);
        entity.setFactoringInvoiceId(factoringInvoiceId);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> getById(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> update(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceStatusHistoryId, FactoringInvoiceStatusHistoryDTO dto) {
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
    public Mono<Void> delete(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .flatMap(repository::delete);
    }
}