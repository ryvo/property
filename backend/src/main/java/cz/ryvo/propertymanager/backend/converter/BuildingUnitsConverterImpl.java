package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingUnitDTO;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingUnitsConverterImpl implements BuildingUnitsConverter {

  private final BuildingUnitConverter converter;

  @Autowired
  public BuildingUnitsConverterImpl(BuildingUnitConverter converter) {
    this.converter = converter;
  }

  @Override
  public List<BuildingUnitDTO> toDTO(List<BuildingUnit> domain) {
    return domain.stream().map(converter::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<BuildingUnit> toEntity(List<BuildingUnitDTO> dto) {
    return dto.stream().map(converter::toEntity).collect(Collectors.toList());
  }
}
