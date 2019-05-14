package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import lombok.NonNull;

import java.util.List;

public interface TenantsConverter {

  List<PersonDTO> toDTO(@NonNull List<Tenant> entity);

  List<Tenant> toEntity(@NonNull List<PersonDTO> dto);
}
