package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import cz.ryvo.propertymanager.backend.util.PersonUtils;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class TenantConverterImpl implements TenantConverter {

  public PersonDTO toDTO(@NonNull Tenant entity) {
    PersonDTO dto = new PersonDTO();
    PersonUtils.mergeEntityIntoDTO(entity, dto);
    return dto;
  }

  public Tenant toEntity(@NonNull PersonDTO dto) {
    Tenant entity = new Tenant();
    PersonUtils.mergeDtoIntoEntity(dto, entity);
    return entity;
  }
}