package com.catalis.core.lending.factoring.core.services.agreement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.mappers.agreement.v1.FactoringAgreementMapper;
import com.catalis.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import com.catalis.core.lending.factoring.models.entities.agreement.v1.FactoringAgreement;
import com.catalis.core.lending.factoring.models.repositories.agreement.v1.FactoringAgreementRepository;
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
    public Mono<FactoringAgreementDTO> getById(Long factoringAgreementId) {
        return repository.findById(factoringAgreementId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringAgreementDTO> update(Long factoringAgreementId, FactoringAgreementDTO dto) {
        return repository.findById(factoringAgreementId)
                .flatMap(existing -> {
                    FactoringAgreement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setFactoringAgreementId(factoringAgreementId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long factoringAgreementId) {
        return repository.deleteById(factoringAgreementId);
    }
}