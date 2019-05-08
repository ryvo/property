package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Address;
import cz.ryvo.propertymanager.backend.domain.Building;
import cz.ryvo.propertymanager.backend.domain.Portfolio;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.BuildingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
class BuildingServiceImpl implements BuildingService {

  @Autowired
  private PortfolioService portfolioService;

  @Autowired
  private BuildingRepository buildingRepository;

  @Override
  public List<Building> listBuildings() {
    return portfolioService.getPortfolio().getBuildings();
  }

  @Override
  public Building getBuilding(@NonNull Long id) {
    Optional<Building> optional = buildingRepository.findById(id);
    return optional.orElseThrow(() -> new NotFoundException("Building not found"));
  }

  @Transactional
  @Override
  public Building createBuilding(@NonNull Building building) {
    Portfolio portfolio = portfolioService.getPortfolio();
    building = buildingRepository.save(building);
    portfolio.getBuildings().add(building);
    portfolioService.savePortfolio(portfolio);
    return building;
  }

  @Transactional
  @Override
  public Building updateBuilding(Long id, Building building) {
    Building persisted = getBuilding(id);
    updateAddress(persisted.getAddress(), building.getAddress());
    buildingRepository.save(persisted);
    return persisted;
  }

  private void updateAddress(Address target, Address source) {
    target.setHouseNumber(source.getHouseNumber());
    target.setRegistryNumber(source.getRegistryNumber());
    target.setStreetName(source.getStreetName());
    target.setTownName(source.getTownName());
    target.setPostalCode(source.getPostalCode());
    target.setCountryName(source.getCountryName());
  }
}