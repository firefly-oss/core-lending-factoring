package com.firefly.core.lending.factoring.core.services.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceSettlementMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceSettlementDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceSettlement;
import com.firefly.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceSettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FactoringInvoiceSettlementServiceImpl implements FactoringInvoiceSettlementService {

    @Autowired
    private FactoringInvoiceSettlementRepository repository;

    @Autowired
    private FactoringInvoiceSettlementMapper mapper;

    @Override
    public Mono<PaginationResponse<FactoringInvoiceSettlementDTO>> findAll(Long factoringAgreementId, Long factoringInvoiceId, FilterRequest<FactoringInvoiceSettlementDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringInvoiceSettlement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceSettlementDTO> create(Long factoringAgreementId, Long factoringInvoiceId, FactoringInvoiceSettlementDTO dto) {
        dto.setFactoringInvoiceId(factoringInvoiceId);
        FactoringInvoiceSettlement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceSettlementDTO> getById(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceSettlementId) {
        return repository.findById(factoringInvoiceSettlementId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceSettlementDTO> update(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceSettlementId, FactoringInvoiceSettlementDTO dto) {
        return repository.findById(factoringInvoiceSettlementId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .flatMap(entity -> {
                    FactoringInvoiceSettlement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setFactoringInvoiceSettlementId(factoringInvoiceSettlementId);
                    updatedEntity.setFactoringInvoiceId(factoringInvoiceId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long factoringAgreementId, Long factoringInvoiceId, Long factoringInvoiceSettlementId) {
        return repository.findById(factoringInvoiceSettlementId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .flatMap(repository::delete);
    }
}