package com.firefly.core.lending.factoring.core.mappers.agreement.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import com.firefly.core.lending.factoring.models.entities.agreement.v1.FactoringAgreement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:37+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringAgreementMapperImpl implements FactoringAgreementMapper {

    @Override
    public FactoringAgreementDTO toDTO(FactoringAgreement agreement) {
        if ( agreement == null ) {
            return null;
        }

        FactoringAgreementDTO.FactoringAgreementDTOBuilder factoringAgreementDTO = FactoringAgreementDTO.builder();

        factoringAgreementDTO.factoringAgreementId( agreement.getFactoringAgreementId() );
        factoringAgreementDTO.contractId( agreement.getContractId() );
        factoringAgreementDTO.customerId( agreement.getCustomerId() );
        factoringAgreementDTO.agreementStatus( agreement.getAgreementStatus() );
        factoringAgreementDTO.startDate( agreement.getStartDate() );
        factoringAgreementDTO.endDate( agreement.getEndDate() );
        factoringAgreementDTO.creditLimit( agreement.getCreditLimit() );
        factoringAgreementDTO.recourse( agreement.getRecourse() );
        factoringAgreementDTO.advanceRate( agreement.getAdvanceRate() );
        factoringAgreementDTO.remarks( agreement.getRemarks() );
        factoringAgreementDTO.createdAt( agreement.getCreatedAt() );
        factoringAgreementDTO.updatedAt( agreement.getUpdatedAt() );

        return factoringAgreementDTO.build();
    }

    @Override
    public FactoringAgreement toEntity(FactoringAgreementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringAgreement.FactoringAgreementBuilder factoringAgreement = FactoringAgreement.builder();

        factoringAgreement.factoringAgreementId( dto.getFactoringAgreementId() );
        factoringAgreement.contractId( dto.getContractId() );
        factoringAgreement.customerId( dto.getCustomerId() );
        factoringAgreement.agreementStatus( dto.getAgreementStatus() );
        factoringAgreement.startDate( dto.getStartDate() );
        factoringAgreement.endDate( dto.getEndDate() );
        factoringAgreement.creditLimit( dto.getCreditLimit() );
        factoringAgreement.recourse( dto.getRecourse() );
        factoringAgreement.advanceRate( dto.getAdvanceRate() );
        factoringAgreement.remarks( dto.getRemarks() );
        factoringAgreement.createdAt( dto.getCreatedAt() );
        factoringAgreement.updatedAt( dto.getUpdatedAt() );

        return factoringAgreement.build();
    }
}
