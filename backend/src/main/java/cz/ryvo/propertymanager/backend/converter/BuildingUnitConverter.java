package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingUnitDTO;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;

public interface BuildingUnitConverter {
  BuildingUnitDTO toDTO(BuildingUnit domain);
  BuildingUnit toEntity(BuildingUnitDTO dto);
}
