package com.firefly.core.lending.factoring.core.mappers.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:37+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringInvoiceStatusHistoryMapperImpl implements FactoringInvoiceStatusHistoryMapper {

    @Override
    public FactoringInvoiceStatusHistoryDTO toDTO(FactoringInvoiceStatusHistory entity) {
        if ( entity == null ) {
            return null;
        }

        FactoringInvoiceStatusHistoryDTO.FactoringInvoiceStatusHistoryDTOBuilder factoringInvoiceStatusHistoryDTO = FactoringInvoiceStatusHistoryDTO.builder();

        factoringInvoiceStatusHistoryDTO.factoringInvoiceStatusHistoryId( entity.getFactoringInvoiceStatusHistoryId() );
        factoringInvoiceStatusHistoryDTO.factoringInvoiceId( entity.getFactoringInvoiceId() );
        factoringInvoiceStatusHistoryDTO.oldStatus( entity.getOldStatus() );
        factoringInvoiceStatusHistoryDTO.newStatus( entity.getNewStatus() );
        factoringInvoiceStatusHistoryDTO.changedAt( entity.getChangedAt() );
        factoringInvoiceStatusHistoryDTO.changedBy( entity.getChangedBy() );
        factoringInvoiceStatusHistoryDTO.reason( entity.getReason() );
        factoringInvoiceStatusHistoryDTO.createdAt( entity.getCreatedAt() );
        factoringInvoiceStatusHistoryDTO.updatedAt( entity.getUpdatedAt() );

        return factoringInvoiceStatusHistoryDTO.build();
    }

    @Override
    public FactoringInvoiceStatusHistory toEntity(FactoringInvoiceStatusHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringInvoiceStatusHistory.FactoringInvoiceStatusHistoryBuilder factoringInvoiceStatusHistory = FactoringInvoiceStatusHistory.builder();

        factoringInvoiceStatusHistory.factoringInvoiceStatusHistoryId( dto.getFactoringInvoiceStatusHistoryId() );
        factoringInvoiceStatusHistory.factoringInvoiceId( dto.getFactoringInvoiceId() );
        factoringInvoiceStatusHistory.oldStatus( dto.getOldStatus() );
        factoringInvoiceStatusHistory.newStatus( dto.getNewStatus() );
        factoringInvoiceStatusHistory.changedAt( dto.getChangedAt() );
        factoringInvoiceStatusHistory.changedBy( dto.getChangedBy() );
        factoringInvoiceStatusHistory.reason( dto.getReason() );
        factoringInvoiceStatusHistory.createdAt( dto.getCreatedAt() );
        factoringInvoiceStatusHistory.updatedAt( dto.getUpdatedAt() );

        return factoringInvoiceStatusHistory.build();
    }
}
