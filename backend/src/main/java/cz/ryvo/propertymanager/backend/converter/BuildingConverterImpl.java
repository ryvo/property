package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.BuildingDTO;
import cz.ryvo.propertymanager.backend.domain.Address;
import cz.ryvo.propertymanager.backend.domain.Building;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class BuildingConverterImpl implements BuildingConverter {

  @Override
  public BuildingDTO toDTO(@NonNull Building entity) {
    BuildingDTO dto = new BuildingDTO();
    dto.setId(entity.getId());
    dto.setHouseNumber(entity.getAddress().getHouseNumber());
    dto.setRegistryNumber(entity.getAddress().getRegistryNumber());
    dto.setStreetName(entity.getAddress().getStreetName());
    dto.setTownName(entity.getAddress().getTownName());
    dto.setPostalCode(entity.getAddress().getPostalCode());
    dto.setCountryName(entity.getAddress().getCountryName());
    return dto;
  }

  @Override
  public Building toEntity(@NonNull BuildingDTO dto) {
    Address address = new Address();
    address.setHouseNumber(dto.getHouseNumber());
    address.setRegistryNumber(dto.getRegistryNumber());
    address.setStreetName(dto.getStreetName());
    address.setTownName(dto.getTownName());
    address.setPostalCode(dto.getPostalCode());
    address.setCountryName(dto.getCountryName());

    Building entity = new Building();
    entity.setAddress(address);

    return entity;
  }
}
