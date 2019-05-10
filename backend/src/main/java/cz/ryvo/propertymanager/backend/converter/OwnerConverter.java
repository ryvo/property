package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import lombok.NonNull;
import org.springframework.stereotype.Component;

public interface OwnerConverter {

  PersonDTO toDTO(@NonNull Owner entity);

  Owner toEntity(@NonNull PersonDTO dto);
}
