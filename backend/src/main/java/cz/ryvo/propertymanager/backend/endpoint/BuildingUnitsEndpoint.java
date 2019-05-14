package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.BuildingUnitDTO;
import cz.ryvo.propertymanager.backend.converter.BuildingUnitConverter;
import cz.ryvo.propertymanager.backend.converter.BuildingUnitsConverter;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import cz.ryvo.propertymanager.backend.service.BuildingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional(readOnly = true)
@RestController
@RequestMapping(path = "/api/v1/buildings/{buildingId}/units")
public class BuildingUnitsEndpoint {

  private final BuildingUnitConverter buildingUnitConverter;
  private final BuildingUnitsConverter buildingUnitsConverter;
  private final BuildingUnitService service;

  @Autowired
  public BuildingUnitsEndpoint(
      BuildingUnitService service,
      BuildingUnitConverter buildingUnitConverter,
      BuildingUnitsConverter buildingUnitsConverter) {
    this.service = service;
    this.buildingUnitConverter = buildingUnitConverter;
    this.buildingUnitsConverter = buildingUnitsConverter;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<BuildingUnitDTO> listBuildingUnits(@PathVariable("buildingId") Long id) {
    return buildingUnitsConverter.toDTO(service.listBuildingUnits(id));
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingUnitDTO createBuildingUnit(@PathVariable("buildingId") Long id, @RequestBody @Validated BuildingUnitDTO dto) {
    BuildingUnit unit = buildingUnitConverter.toEntity(dto);
    return buildingUnitConverter.toDTO(service.createBuildingUnit(id, unit));
  }

  @Transactional
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingUnitDTO updateBuildingUnit(@PathVariable("id") long id, @RequestBody @Validated BuildingUnitDTO dto) {
    BuildingUnit unit = buildingUnitConverter.toEntity(dto);
    return buildingUnitConverter.toDTO(service.updateBuildingUnit(id, unit));
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public BuildingUnitDTO getBuildingUnit(@PathVariable("id") long id) {
    return buildingUnitConverter.toDTO(service.getBuildingUnit(id));
  }
}
