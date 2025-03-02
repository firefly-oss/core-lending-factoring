package com.catalis.core.lending.factoring.core.services.debtor.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.mappers.debtor.v1.FactoringDebtorMapper;
import com.catalis.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import com.catalis.core.lending.factoring.models.entities.debtor.v1.FactoringDebtor;
import com.catalis.core.lending.factoring.models.repositories.debtor.v1.FactoringDebtorRepository;
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
    public Mono<PaginationResponse<FactoringDebtorDTO>> findAll(Long factoringAgreementId, FilterRequest<FactoringDebtorDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringDebtor.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringDebtorDTO> create(Long factoringAgreementId, FactoringDebtorDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringDebtor entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringDebtorDTO> getById(Long factoringAgreementId, Long factoringDebtorId) {
        return repository.findById(factoringDebtorId)
                .filter(entity -> entity.getFactoringAgreementId().equals(factoringAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringDebtorDTO> update(Long factoringAgreementId, Long factoringDebtorId, FactoringDebtorDTO dto) {
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
    public Mono<Void> delete(Long factoringAgreementId, Long factoringDebtorId) {
        return repository.findById(factoringDebtorId)
                .filter(entity -> entity.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(repository::delete);
    }
}