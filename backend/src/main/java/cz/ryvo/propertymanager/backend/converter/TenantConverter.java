package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import lombok.NonNull;

public interface TenantConverter {

  PersonDTO toDTO(@NonNull Tenant entity);

  Tenant toEntity(@NonNull PersonDTO dto);
}
