package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
class TenantsConverterImpl implements TenantsConverter {

  private final TenantConverter converter;

  @Autowired
  public TenantsConverterImpl(TenantConverter converter) {
    this.converter = converter;
  }

  public List<PersonDTO> toDTO(@NonNull List<Tenant> entity) {
    return entity.stream().map(converter::toDTO).collect(toList());
  }

  public List<Tenant> toEntity(@NonNull List<PersonDTO> dto) {
    return dto.stream().map(converter::toEntity).collect(toList());
  }
}
