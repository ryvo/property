package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.*;
import cz.ryvo.propertymanager.backend.exception.BadRequestException;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.LeaseRepository;
import cz.ryvo.propertymanager.backend.util.AuthUtils;
import cz.ryvo.propertymanager.backend.util.LeaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class LeaseServiceImpl implements LeaseService {

  private final LeaseRepository repository;
  private final BuildingUnitService buildingUnitService;
  private final TenantService tenantService;

  @Autowired
  public LeaseServiceImpl(
        LeaseRepository repository,
        BuildingUnitService buildingUnitService,
        TenantService tenantService) {
    this.repository = repository;
    this.buildingUnitService = buildingUnitService;
    this.tenantService = tenantService;
  }

  @Override
  public List<Lease> listLeases(long buildingUnitId) {
    return repository.findAllByBuildingUnitIdOrderByStartDateDesc(buildingUnitId);
  }

  @Override
  public List<Lease> listActiveLeases(long buildingId) {
    long portfolioId = AuthUtils.getPortfolioId();
    return repository.findAllByBuildingId(portfolioId, buildingId);
  }

  @Override
  public Lease getLease(long id) {
    Optional<Lease> optional = repository.findById(id);
    return optional.orElseThrow(() -> new NotFoundException("Lease not found"));
  }

  @Override
  public Lease createLease(long buildingUnitId, Lease lease) {
    Assert.notNull(lease, "Lease must not be null");

    BuildingUnit buildingUnit =  buildingUnitService.getBuildingUnit(buildingUnitId);
    // Check portfolio
    Portfolio portfolio = buildingUnit.getBuilding().getPortfolio();
    if (!portfolio.getId().equals(AuthUtils.getPortfolioId())) {
      throw new NotFoundException("Building unit not found");
    }
    // Check tenant
    Tenant tenant = tenantService.getTenant(lease.getTenant().getId());
    if (!tenant.getPortfolio().getId().equals(AuthUtils.getPortfolioId())) {
      throw new NotFoundException("Tenant unit not found");
    }
    // Check conflicting leases
    List<Lease> leases = repository.findAllByBuildingUnitIdOrderByStartDateDesc(buildingUnitId);
    Optional<Lease> conflictingLease = leases.stream()
            .filter(p -> p.getEndDate() == null || p.getEndDate().compareTo(lease.getStartDate()) >= 0)
            .findAny();
    if (conflictingLease.isPresent()) {
      throw new BadRequestException("Lease start or end date is conflicting with an existing lease.");
    }
    // Create lease
    lease.setBuildingUnit(buildingUnit);
    lease.setTenant(tenant);
    return repository.save(lease);
  }

  private boolean doLeaseDateOverlaps(Lease existingLease, Lease newLease) {
    return existingLease.getEndDate() == null || existingLease.getEndDate().compareTo(newLease.getStartDate()) >= 0;
  }

  @Override
  public Lease updateLease(long id, Lease lease) {
    // Find persisted lease
    Optional<Lease> optional = repository.findById(id);
    if (!optional.isPresent()) {
      throw new NotFoundException("Lease not found");
    }
    Lease persistedLease = optional.get();

    // Check portfolio
    Portfolio portfolio = persistedLease.getBuildingUnit().getBuilding().getPortfolio();
    if (!portfolio.getId().equals(AuthUtils.getPortfolioId())) {
      throw new NotFoundException("Lease not found");
    }

    // Update lease properties
    LeaseUtils.merge(lease, persistedLease);
    Long newTenantId = lease.getTenant().getId();
    if (!persistedLease.getTenant().getId().equals(newTenantId)) {
      Tenant newTenant = tenantService.getTenant(newTenantId);
      persistedLease.setTenant(newTenant);
    }

    return repository.save(persistedLease);
  }
}
