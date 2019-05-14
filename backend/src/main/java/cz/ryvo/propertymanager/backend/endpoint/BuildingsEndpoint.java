package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.converter.BuildingConverter;
import cz.ryvo.propertymanager.backend.converter.BuildingsConverter;
import cz.ryvo.propertymanager.backend.domain.Building;
import cz.ryvo.propertymanager.backend.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional(readOnly = true)
@RestController
@RequestMapping(path = "/api/v1/buildings")
public class BuildingsEndpoint {

  private final BuildingConverter buildingConverter;

  private final BuildingsConverter buildingsConverter;

  private final BuildingService service;

  @Autowired
  public BuildingsEndpoint(
      BuildingService service,
      BuildingConverter buildingConverter,
      BuildingsConverter buildingsConverter) {
    this.service = service;
    this.buildingConverter = buildingConverter;
    this.buildingsConverter = buildingsConverter;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<BuildingDTO> listBuildings() {
    return buildingsConverter.toDTO(service.listBuildings());
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingDTO createBuilding(@RequestBody @Validated BuildingDTO dto) {
    Building building = buildingConverter.toEntity(dto);
    return buildingConverter.toDTO(service.createBuilding(building));
  }

  @Transactional
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingDTO updateBuilding(@PathVariable("id") long id, @RequestBody @Validated BuildingDTO dto) {
    Building building = buildingConverter.toEntity(dto);
    return buildingConverter.toDTO(service.updateBuilding(id, building));
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public BuildingDTO getBuilding(@PathVariable("id") long id) {
    return buildingConverter.toDTO(service.getBuilding(id));
  }
}
