package com.firefly.core.lending.factoring.core.mappers.debtor.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import com.firefly.core.lending.factoring.models.entities.debtor.v1.FactoringDebtor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:39:48+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringDebtorMapperImpl implements FactoringDebtorMapper {

    @Override
    public FactoringDebtorDTO toDTO(FactoringDebtor entity) {
        if ( entity == null ) {
            return null;
        }

        FactoringDebtorDTO.FactoringDebtorDTOBuilder factoringDebtorDTO = FactoringDebtorDTO.builder();

        factoringDebtorDTO.factoringDebtorId( entity.getFactoringDebtorId() );
        factoringDebtorDTO.factoringAgreementId( entity.getFactoringAgreementId() );
        factoringDebtorDTO.debtorCustomerId( entity.getDebtorCustomerId() );
        factoringDebtorDTO.debtorName( entity.getDebtorName() );
        factoringDebtorDTO.debtorCreditLimit( entity.getDebtorCreditLimit() );
        factoringDebtorDTO.remarks( entity.getRemarks() );
        factoringDebtorDTO.createdAt( entity.getCreatedAt() );
        factoringDebtorDTO.updatedAt( entity.getUpdatedAt() );

        return factoringDebtorDTO.build();
    }

    @Override
    public FactoringDebtor toEntity(FactoringDebtorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringDebtor.FactoringDebtorBuilder factoringDebtor = FactoringDebtor.builder();

        factoringDebtor.factoringDebtorId( dto.getFactoringDebtorId() );
        factoringDebtor.factoringAgreementId( dto.getFactoringAgreementId() );
        factoringDebtor.debtorCustomerId( dto.getDebtorCustomerId() );
        factoringDebtor.debtorName( dto.getDebtorName() );
        factoringDebtor.debtorCreditLimit( dto.getDebtorCreditLimit() );
        factoringDebtor.remarks( dto.getRemarks() );
        factoringDebtor.createdAt( dto.getCreatedAt() );
        factoringDebtor.updatedAt( dto.getUpdatedAt() );

        return factoringDebtor.build();
    }
}
