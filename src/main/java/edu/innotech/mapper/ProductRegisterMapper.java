package edu.innotech.mapper;

import org.mapstruct.Mapper;
import edu.innotech.dto.ProductRegisterDTO;
import edu.innotech.model.TppProductRegister;

@Mapper(componentModel = "spring")
public interface ProductRegisterMapper {
    TppProductRegister toEntity(ProductRegisterDTO dto);


}