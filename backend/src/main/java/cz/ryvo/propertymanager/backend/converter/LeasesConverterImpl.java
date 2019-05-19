package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.LeaseDTO;
import cz.ryvo.propertymanager.backend.domain.Lease;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
class LeasesConverterImpl implements LeasesConverter {

  private final LeaseConverter converter;

  public LeasesConverterImpl(LeaseConverter converter) {
    this.converter = converter;
  }

  @Override
  public List<LeaseDTO> toDTO(@NonNull List<Lease> entity) {
    return entity.stream().map(converter::toDTO).collect(toList());
  }

  @Override
  public List<Lease> toEntity(@NonNull List<LeaseDTO> dto) {
    return dto.stream().map(converter::toEntity).collect(toList());
  }
}
