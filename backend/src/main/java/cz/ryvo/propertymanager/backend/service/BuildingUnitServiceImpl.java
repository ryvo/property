package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Building;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.BuildingUnitRepository;
import cz.ryvo.propertymanager.backend.util.BuildingUnitUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BuildingUnitServiceImpl implements BuildingUnitService {

  private final BuildingService service;
  private final BuildingUnitRepository repository;

  @Autowired
  public BuildingUnitServiceImpl(BuildingService service, BuildingUnitRepository repository) {
    this.service = service;
    this.repository= repository;
  }

  @Override
  public List<BuildingUnit> listBuildings(long buildingId) {
    return service.getBuilding(buildingId).getUnits();
  }

  @Override
  public BuildingUnit getBuildingUnit(long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("Building unit not found"));
  }

  @Override
  public BuildingUnit createBuildingUnit(long buildingId, @NonNull BuildingUnit buildingUnit) {
    Building building = service.getBuilding(buildingId);
    buildingUnit.setBuilding(building);
    return repository.save(buildingUnit);
  }

  @Override
  public BuildingUnit updateBuildingUnit(long id, BuildingUnit buildingUnit) {
    BuildingUnit persisted = getBuildingUnit(id);
    BuildingUnitUtils.merge(buildingUnit, persisted);
    return repository.save(persisted);
  }
}
