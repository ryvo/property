package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.domain.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
class BuildingsConverterImpl implements BuildingsConverter {

  private final BuildingConverter converter;

  @Autowired
  public BuildingsConverterImpl(BuildingConverter converter) {
    this.converter = converter;
  }

  public List<BuildingDTO> toDTO(List<Building> domain) {
    return domain.stream().map(converter::toDTO).collect(toList());
  }
}
