package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class OwnerConverterImpl extends PersonConverter implements OwnerConverter {

  public PersonDTO toDTO(@NonNull Owner entity) {
    PersonDTO dto = new PersonDTO();
    dto.setId(entity.getId());
    dto.setType(entity.getType());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setCompanyName(entity.getCompanyName());
    dto.setHouseNumber(entity.getAddress().getHouseNumber());
    dto.setRegistryNumber(entity.getAddress().getRegistryNumber());
    dto.setStreetName(entity.getAddress().getStreetName());
    dto.setTownName(entity.getAddress().getTownName());
    dto.setPostalCode(entity.getAddress().getPostalCode());
    dto.setCountryName(entity.getAddress().getCountryName());
    return dto;
  }

  public Owner toEntity(@NonNull PersonDTO dto) {
    Owner entity = new Owner();
    entity.setType(dto.getType());
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setCompanyName(dto.getCompanyName());
    mergeAddress(dto, entity.getAddress());
    mergeAddress(dto, entity.getAddress());
    return entity;
  }
}