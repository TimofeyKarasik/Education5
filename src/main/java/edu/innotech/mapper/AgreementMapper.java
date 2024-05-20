package edu.innotech.mapper;

import org.mapstruct.Mapper;
import edu.innotech.dto.AgreementDTO;
import edu.innotech.model.Agreement;

@Mapper(componentModel = "spring")
public interface AgreementMapper {

    Agreement toEntity(AgreementDTO dto);
}