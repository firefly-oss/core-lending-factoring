package com.catalis.core.lending.factoring.core.services.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceMapper;
import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import com.catalis.core.lending.factoring.models.entities.invoice.v1.FactoringInvoice;
import com.catalis.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceRepository;
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
    public Mono<PaginationResponse<FactoringInvoiceDTO>> findAll(Long factoringAgreementId, FilterRequest<FactoringInvoiceDTO> filterRequest) {
        filterRequest.getFilters().setFactoringAgreementId(factoringAgreementId);
        return FilterUtils.createFilter(
                FactoringInvoice.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceDTO> create(Long factoringAgreementId, FactoringInvoiceDTO dto) {
        dto.setFactoringAgreementId(factoringAgreementId);
        FactoringInvoice entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .doOnNext(invoice -> invoice.setFactoringInvoiceId(null))
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceDTO> getById(Long factoringAgreementId, Long factoringInvoiceId) {
        return repository.findById(factoringInvoiceId)
                .filter(invoice -> factoringAgreementId.equals(invoice.getFactoringAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceDTO> update(Long factoringAgreementId, Long factoringInvoiceId, FactoringInvoiceDTO dto) {
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
    public Mono<Void> delete(Long factoringAgreementId, Long factoringInvoiceId) {
        return repository.findById(factoringInvoiceId)
                .filter(invoice -> factoringAgreementId.equals(invoice.getFactoringAgreementId()))
                .flatMap(repository::delete);
    }
}