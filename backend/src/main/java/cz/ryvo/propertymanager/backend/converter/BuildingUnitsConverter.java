package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingUnitDTO;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;

import java.util.List;

public interface BuildingUnitsConverter {
  List<BuildingUnitDTO> toDTO(List<BuildingUnit> domain);
  List<BuildingUnit> toEntity(List<BuildingUnitDTO> dto);
}
