package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.domain.Building;

import java.util.List;

public interface BuildingsConverter {
  List<BuildingDTO> toDTO(List<Building> domain);
}
