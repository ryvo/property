package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import lombok.NonNull;

import java.util.List;

public interface OwnersConverter {

  List<PersonDTO> toDTO(@NonNull List<Owner> entity);

  List<Owner> toEntity(@NonNull List<PersonDTO> dto);
}
