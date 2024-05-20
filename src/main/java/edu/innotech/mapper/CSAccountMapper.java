package edu.innotech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import edu.innotech.dto.CorporateSettlementAccount;
import edu.innotech.dto.ProductRegisterDTO;

@Mapper(componentModel = "spring")
public interface CSAccountMapper {
    @Mapping( target="type", source="registryTypeCode")
    ProductRegisterDTO toRegisterDTO(CorporateSettlementAccount csa);
}