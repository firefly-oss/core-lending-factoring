package com.firefly.core.lending.factoring.core.services.invoice.v1;
import java.util.UUID;
import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.factoring.core.mappers.invoice.v1.FactoringInvoiceStatusHistoryMapper;
import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import com.firefly.core.lending.factoring.models.repositories.invoice.v1.FactoringInvoiceStatusHistoryRepository;
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
    public Mono<PaginationResponse<FactoringInvoiceStatusHistoryDTO>> findAll(UUID factoringAgreementId, UUID factoringInvoiceId, FilterRequest<FactoringInvoiceStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setFactoringInvoiceId(factoringInvoiceId);
        return FilterUtils.createFilter(
                FactoringInvoiceStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> create(UUID factoringAgreementId, UUID factoringInvoiceId, FactoringInvoiceStatusHistoryDTO dto) {
        FactoringInvoiceStatusHistory entity = mapper.toEntity(dto);
        entity.setFactoringInvoiceId(factoringInvoiceId);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> getById(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<FactoringInvoiceStatusHistoryDTO> update(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId, FactoringInvoiceStatusHistoryDTO dto) {
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
    public Mono<Void> delete(UUID factoringAgreementId, UUID factoringInvoiceId, UUID factoringInvoiceStatusHistoryId) {
        return repository.findById(factoringInvoiceStatusHistoryId)
                .filter(entity -> entity.getFactoringInvoiceId().equals(factoringInvoiceId))
                .flatMap(repository::delete);
    }
}