package com.catalis.core.lending.factoring.core.services.fee.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.mappers.fee.v1.FactoringFeeMapper;
import com.catalis.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import com.catalis.core.lending.factoring.models.entities.fee.v1.FactoringFee;
import com.catalis.core.lending.factoring.models.repositories.fee.v1.FactoringFeeRepository;
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
    public Mono<PaginationResponse<FactoringFeeDTO>> findAll(Long factoringAgreementId, FilterRequest<FactoringFeeDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringFee.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringFeeDTO> create(Long factoringAgreementId, FactoringFeeDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringFee entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringFeeDTO> getById(Long factoringAgreementId, Long factoringFeeId) {
        return repository.findById(factoringFeeId)
                .filter(fee -> fee.getFactoringAgreementId().equals(factoringAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringFeeDTO> update(Long factoringAgreementId, Long factoringFeeId, FactoringFeeDTO dto) {
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
    public Mono<Void> delete(Long factoringAgreementId, Long factoringFeeId) {
        return repository.findById(factoringFeeId)
                .filter(fee -> fee.getFactoringAgreementId().equals(factoringAgreementId))
                .flatMap(repository::delete);
    }
}