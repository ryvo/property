package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Building;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BuildingService {
  @NonNull List<Building> listBuildings();
  @NonNull Building getBuilding(@NonNull Long id);
  @NonNull Building createBuilding(@NonNull Building building);
  @NonNull Building updateBuilding(Long id, Building building);
}
