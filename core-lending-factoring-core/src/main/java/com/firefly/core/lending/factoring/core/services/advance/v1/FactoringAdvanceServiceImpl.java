package com.firefly.core.lending.factoring.core.services.advance.v1;

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
    public Mono<PaginationResponse<FactoringAdvanceDTO>> findAll(Long factoringAgreementId, Long factoringInvoiceId, FilterRequest<FactoringAdvanceDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringAdvance.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringAdvanceDTO> create(Long factoringAgreementId, Long factoringInvoiceId, FactoringAdvanceDTO dto) {
        FactoringAdvance entity = mapper.toEntity(dto);
        entity.setFactoringInvoiceId(factoringInvoiceId);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAdvanceDTO> getById(Long factoringAgreementId, Long factoringInvoiceId, Long factoringAdvanceId) {
        return repository.findById(factoringAdvanceId)
                .filter(advance -> factoringInvoiceId.equals(advance.getFactoringInvoiceId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAdvanceDTO> update(Long factoringAgreementId, Long factoringInvoiceId, Long factoringAdvanceId, FactoringAdvanceDTO dto) {
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
    public Mono<Void> delete(Long factoringAgreementId, Long factoringInvoiceId, Long factoringAdvanceId) {
        return repository.findById(factoringAdvanceId)
                .filter(advance -> factoringInvoiceId.equals(advance.getFactoringInvoiceId()))
                .flatMap(repository::delete);
    }
}