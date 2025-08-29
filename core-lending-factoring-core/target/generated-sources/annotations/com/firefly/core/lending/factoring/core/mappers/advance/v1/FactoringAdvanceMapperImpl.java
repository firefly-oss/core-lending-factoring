package com.firefly.core.lending.factoring.core.mappers.advance.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.advance.v1.FactoringAdvanceDTO;
import com.firefly.core.lending.factoring.models.entities.advance.v1.FactoringAdvance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:39:48+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringAdvanceMapperImpl implements FactoringAdvanceMapper {

    @Override
    public FactoringAdvanceDTO toDTO(FactoringAdvance advance) {
        if ( advance == null ) {
            return null;
        }

        FactoringAdvanceDTO.FactoringAdvanceDTOBuilder factoringAdvanceDTO = FactoringAdvanceDTO.builder();

        factoringAdvanceDTO.factoringAdvanceId( advance.getFactoringAdvanceId() );
        factoringAdvanceDTO.factoringInvoiceId( advance.getFactoringInvoiceId() );
        factoringAdvanceDTO.transactionId( advance.getTransactionId() );
        factoringAdvanceDTO.advanceAmount( advance.getAdvanceAmount() );
        factoringAdvanceDTO.advanceDate( advance.getAdvanceDate() );
        factoringAdvanceDTO.isFinalAdvance( advance.getIsFinalAdvance() );
        factoringAdvanceDTO.note( advance.getNote() );
        factoringAdvanceDTO.createdAt( advance.getCreatedAt() );
        factoringAdvanceDTO.updatedAt( advance.getUpdatedAt() );

        return factoringAdvanceDTO.build();
    }

    @Override
    public FactoringAdvance toEntity(FactoringAdvanceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringAdvance.FactoringAdvanceBuilder factoringAdvance = FactoringAdvance.builder();

        factoringAdvance.factoringAdvanceId( dto.getFactoringAdvanceId() );
        factoringAdvance.factoringInvoiceId( dto.getFactoringInvoiceId() );
        factoringAdvance.transactionId( dto.getTransactionId() );
        factoringAdvance.advanceAmount( dto.getAdvanceAmount() );
        factoringAdvance.advanceDate( dto.getAdvanceDate() );
        factoringAdvance.isFinalAdvance( dto.getIsFinalAdvance() );
        factoringAdvance.note( dto.getNote() );
        factoringAdvance.createdAt( dto.getCreatedAt() );
        factoringAdvance.updatedAt( dto.getUpdatedAt() );

        return factoringAdvance.build();
    }
}
