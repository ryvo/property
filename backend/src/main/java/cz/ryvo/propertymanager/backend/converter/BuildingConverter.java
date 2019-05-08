package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.domain.Building;

public interface BuildingConverter {
  BuildingDTO toDTO(Building domain);
  Building toEntity(BuildingDTO dto);
}
