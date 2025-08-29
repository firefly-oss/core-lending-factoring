package com.firefly.core.lending.factoring.core.mappers.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:37+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringInvoiceMapperImpl implements FactoringInvoiceMapper {

    @Override
    public FactoringInvoiceDTO toDTO(FactoringInvoice entity) {
        if ( entity == null ) {
            return null;
        }

        FactoringInvoiceDTO.FactoringInvoiceDTOBuilder factoringInvoiceDTO = FactoringInvoiceDTO.builder();

        factoringInvoiceDTO.factoringInvoiceId( entity.getFactoringInvoiceId() );
        factoringInvoiceDTO.factoringAgreementId( entity.getFactoringAgreementId() );
        factoringInvoiceDTO.factoringDebtorId( entity.getFactoringDebtorId() );
        factoringInvoiceDTO.invoiceNumber( entity.getInvoiceNumber() );
        factoringInvoiceDTO.invoiceDate( entity.getInvoiceDate() );
        factoringInvoiceDTO.dueDate( entity.getDueDate() );
        factoringInvoiceDTO.invoiceAmount( entity.getInvoiceAmount() );
        factoringInvoiceDTO.assignedAmount( entity.getAssignedAmount() );
        factoringInvoiceDTO.currencyCode( entity.getCurrencyCode() );
        factoringInvoiceDTO.invoiceStatus( entity.getInvoiceStatus() );
        factoringInvoiceDTO.isAcceptedByDebtor( entity.getIsAcceptedByDebtor() );
        factoringInvoiceDTO.acceptanceDate( entity.getAcceptanceDate() );
        factoringInvoiceDTO.documentReference( entity.getDocumentReference() );
        factoringInvoiceDTO.createdAt( entity.getCreatedAt() );
        factoringInvoiceDTO.updatedAt( entity.getUpdatedAt() );

        return factoringInvoiceDTO.build();
    }

    @Override
    public FactoringInvoice toEntity(FactoringInvoiceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringInvoice.FactoringInvoiceBuilder factoringInvoice = FactoringInvoice.builder();

        factoringInvoice.factoringInvoiceId( dto.getFactoringInvoiceId() );
        factoringInvoice.factoringAgreementId( dto.getFactoringAgreementId() );
        factoringInvoice.factoringDebtorId( dto.getFactoringDebtorId() );
        factoringInvoice.invoiceNumber( dto.getInvoiceNumber() );
        factoringInvoice.invoiceDate( dto.getInvoiceDate() );
        factoringInvoice.dueDate( dto.getDueDate() );
        factoringInvoice.invoiceAmount( dto.getInvoiceAmount() );
        factoringInvoice.assignedAmount( dto.getAssignedAmount() );
        factoringInvoice.currencyCode( dto.getCurrencyCode() );
        factoringInvoice.invoiceStatus( dto.getInvoiceStatus() );
        factoringInvoice.isAcceptedByDebtor( dto.getIsAcceptedByDebtor() );
        factoringInvoice.acceptanceDate( dto.getAcceptanceDate() );
        factoringInvoice.documentReference( dto.getDocumentReference() );
        factoringInvoice.createdAt( dto.getCreatedAt() );
        factoringInvoice.updatedAt( dto.getUpdatedAt() );

        return factoringInvoice.build();
    }
}
