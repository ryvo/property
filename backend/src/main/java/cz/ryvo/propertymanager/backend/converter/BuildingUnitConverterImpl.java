package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingUnitDTO;
import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import org.springframework.stereotype.Component;

@Component
class BuildingUnitConverterImpl implements BuildingUnitConverter {

  @Override
  public BuildingUnitDTO toDTO(BuildingUnit domain) {
    BuildingUnitDTO dto = new BuildingUnitDTO();
    dto.setId(domain.getId());
    dto.setType(dto.getType());
    dto.setName(domain.getName());
    dto.setStorey(domain.getStorey());
    dto.setArea(domain.getArea());
    return dto;
  }

  @Override
  public BuildingUnit toEntity(BuildingUnitDTO dto) {
    BuildingUnit domain = new BuildingUnit();
    domain.setId(dto.getId());
    domain.setType(dto.getType());
    domain.setName(dto.getName());
    domain.setStorey(dto.getStorey());
    domain.setArea(dto.getArea());
    return domain;
  }
}
