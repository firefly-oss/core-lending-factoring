package com.firefly.core.lending.factoring.core.mappers.agreement.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.agreement.v1.FactoringAgreementDTO;
import com.firefly.core.lending.factoring.models.entities.agreement.v1.FactoringAgreement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringAgreementMapper {
    FactoringAgreementDTO toDTO(FactoringAgreement agreement);
    FactoringAgreement toEntity(FactoringAgreementDTO dto);
}
