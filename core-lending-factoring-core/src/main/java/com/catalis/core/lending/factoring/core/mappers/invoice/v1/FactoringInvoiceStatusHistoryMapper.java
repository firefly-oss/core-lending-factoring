package com.catalis.core.lending.factoring.core.mappers.invoice.v1;

import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.catalis.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringInvoiceStatusHistoryMapper {
    FactoringInvoiceStatusHistoryDTO toDTO(FactoringInvoiceStatusHistory entity);
    FactoringInvoiceStatusHistory toEntity(FactoringInvoiceStatusHistoryDTO dto);
}