package com.firefly.core.lending.factoring.core.mappers.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceSettlementDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceSettlement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:37+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringInvoiceSettlementMapperImpl implements FactoringInvoiceSettlementMapper {

    @Override
    public FactoringInvoiceSettlementDTO toDTO(FactoringInvoiceSettlement entity) {
        if ( entity == null ) {
            return null;
        }

        FactoringInvoiceSettlementDTO.FactoringInvoiceSettlementDTOBuilder factoringInvoiceSettlementDTO = FactoringInvoiceSettlementDTO.builder();

        factoringInvoiceSettlementDTO.factoringInvoiceSettlementId( entity.getFactoringInvoiceSettlementId() );
        factoringInvoiceSettlementDTO.factoringInvoiceId( entity.getFactoringInvoiceId() );
        factoringInvoiceSettlementDTO.transactionId( entity.getTransactionId() );
        factoringInvoiceSettlementDTO.settlementDate( entity.getSettlementDate() );
        factoringInvoiceSettlementDTO.settlementAmount( entity.getSettlementAmount() );
        factoringInvoiceSettlementDTO.feesDeducted( entity.getFeesDeducted() );
        factoringInvoiceSettlementDTO.netToClient( entity.getNetToClient() );
        factoringInvoiceSettlementDTO.isClosed( entity.getIsClosed() );
        factoringInvoiceSettlementDTO.note( entity.getNote() );
        factoringInvoiceSettlementDTO.createdAt( entity.getCreatedAt() );
        factoringInvoiceSettlementDTO.updatedAt( entity.getUpdatedAt() );

        return factoringInvoiceSettlementDTO.build();
    }

    @Override
    public FactoringInvoiceSettlement toEntity(FactoringInvoiceSettlementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringInvoiceSettlement.FactoringInvoiceSettlementBuilder factoringInvoiceSettlement = FactoringInvoiceSettlement.builder();

        factoringInvoiceSettlement.factoringInvoiceSettlementId( dto.getFactoringInvoiceSettlementId() );
        factoringInvoiceSettlement.factoringInvoiceId( dto.getFactoringInvoiceId() );
        factoringInvoiceSettlement.transactionId( dto.getTransactionId() );
        factoringInvoiceSettlement.settlementDate( dto.getSettlementDate() );
        factoringInvoiceSettlement.settlementAmount( dto.getSettlementAmount() );
        factoringInvoiceSettlement.feesDeducted( dto.getFeesDeducted() );
        factoringInvoiceSettlement.netToClient( dto.getNetToClient() );
        factoringInvoiceSettlement.isClosed( dto.getIsClosed() );
        factoringInvoiceSettlement.note( dto.getNote() );
        factoringInvoiceSettlement.createdAt( dto.getCreatedAt() );
        factoringInvoiceSettlement.updatedAt( dto.getUpdatedAt() );

        return factoringInvoiceSettlement.build();
    }
}
