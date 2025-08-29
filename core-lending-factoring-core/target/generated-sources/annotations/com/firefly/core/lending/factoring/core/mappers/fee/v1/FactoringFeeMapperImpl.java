package com.firefly.core.lending.factoring.core.mappers.fee.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import com.firefly.core.lending.factoring.models.entities.fee.v1.FactoringFee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class FactoringFeeMapperImpl implements FactoringFeeMapper {

    @Override
    public FactoringFeeDTO toDTO(FactoringFee entity) {
        if ( entity == null ) {
            return null;
        }

        FactoringFeeDTO.FactoringFeeDTOBuilder factoringFeeDTO = FactoringFeeDTO.builder();

        factoringFeeDTO.factoringFeeId( entity.getFactoringFeeId() );
        factoringFeeDTO.factoringAgreementId( entity.getFactoringAgreementId() );
        factoringFeeDTO.feeType( entity.getFeeType() );
        factoringFeeDTO.feeRate( entity.getFeeRate() );
        factoringFeeDTO.minFee( entity.getMinFee() );
        factoringFeeDTO.maxFee( entity.getMaxFee() );
        factoringFeeDTO.isActive( entity.getIsActive() );
        factoringFeeDTO.note( entity.getNote() );
        factoringFeeDTO.createdAt( entity.getCreatedAt() );
        factoringFeeDTO.updatedAt( entity.getUpdatedAt() );

        return factoringFeeDTO.build();
    }

    @Override
    public FactoringFee toEntity(FactoringFeeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactoringFee.FactoringFeeBuilder factoringFee = FactoringFee.builder();

        factoringFee.factoringFeeId( dto.getFactoringFeeId() );
        factoringFee.factoringAgreementId( dto.getFactoringAgreementId() );
        factoringFee.feeType( dto.getFeeType() );
        factoringFee.feeRate( dto.getFeeRate() );
        factoringFee.minFee( dto.getMinFee() );
        factoringFee.maxFee( dto.getMaxFee() );
        factoringFee.isActive( dto.getIsActive() );
        factoringFee.note( dto.getNote() );
        factoringFee.createdAt( dto.getCreatedAt() );
        factoringFee.updatedAt( dto.getUpdatedAt() );

        return factoringFee.build();
    }
}
