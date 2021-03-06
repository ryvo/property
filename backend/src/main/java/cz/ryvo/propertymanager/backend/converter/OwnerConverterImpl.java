package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.util.PersonUtils;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class OwnerConverterImpl implements OwnerConverter {

  public PersonDTO toDTO(@NonNull Owner entity) {
    PersonDTO dto = new PersonDTO();
    PersonUtils.mergeEntityIntoDTO(entity, dto);
    return dto;
  }

  public Owner toEntity(@NonNull PersonDTO dto) {
    Owner entity = new Owner();
    PersonUtils.mergeDtoIntoEntity(dto, entity);
    return entity;
  }
}