package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Owner;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
class OwnersConverterImpl implements OwnersConverter {

  private final OwnerConverter converter;

  @Autowired
  public OwnersConverterImpl(OwnerConverter converter) {
    this.converter = converter;
  }

  public List<PersonDTO> toDTO(@NonNull List<Owner> entity) {
    return entity.stream().map(converter::toDTO).collect(toList());
  }

  public List<Owner> toEntity(@NonNull List<PersonDTO> dto) {
    return dto.stream().map(converter::toEntity).collect(toList());
  }
}
