package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BuildingUnitService {
  @NonNull List<BuildingUnit> listBuildingUnits(long buildingId);
  @NonNull BuildingUnit getBuildingUnit(long id);
  @NonNull BuildingUnit createBuildingUnit(long buildingId, @NonNull BuildingUnit buildingUnit);
  @NonNull BuildingUnit updateBuildingUnit(long id, BuildingUnit buildingUnit);
}
