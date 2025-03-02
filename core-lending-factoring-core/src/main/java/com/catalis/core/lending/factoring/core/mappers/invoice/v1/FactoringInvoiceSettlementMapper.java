package com.catalis.core.lending.factoring.core.mappers.invoice.v1;

import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceSettlementDTO;
import com.catalis.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceSettlement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringInvoiceSettlementMapper {
    FactoringInvoiceSettlementDTO toDTO(FactoringInvoiceSettlement entity);
    FactoringInvoiceSettlement toEntity(FactoringInvoiceSettlementDTO dto);
}
