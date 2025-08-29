package com.firefly.core.lending.factoring.core.mappers.debtor.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.debtor.v1.FactoringDebtorDTO;
import com.firefly.core.lending.factoring.models.entities.debtor.v1.FactoringDebtor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringDebtorMapper {
    FactoringDebtorDTO toDTO(FactoringDebtor entity);
    FactoringDebtor toEntity(FactoringDebtorDTO dto);
}