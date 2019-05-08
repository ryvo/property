package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.converter.BuildingConverter;
import cz.ryvo.propertymanager.backend.domain.Building;
import cz.ryvo.propertymanager.backend.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/buildings")
public class BuildingsEndpoint {

  @Autowired
  private BuildingConverter converter;

  @Autowired
  private BuildingService service;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<Building> listBuildings() {
    return service.listBuildings();
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingDTO createBuilding(@RequestBody @Validated BuildingDTO dto) {
    Building building = converter.toEntity(dto);
    return converter.toDTO(service.createBuilding(building));
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public BuildingDTO updateBuilding(@PathVariable("id") Long id, @Validated BuildingDTO dto) {
    Building building = converter.toEntity(dto);
    return converter.toDTO(service.updateBuilding(id, building));
  }
}
