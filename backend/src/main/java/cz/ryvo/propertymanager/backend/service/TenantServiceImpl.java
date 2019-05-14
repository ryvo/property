package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Portfolio;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.TenantRepository;
import cz.ryvo.propertymanager.backend.util.TenantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TenantServiceImpl implements TenantService {

  private final PortfolioService portfolioService;
  private final TenantRepository repository;

  @Autowired
  public TenantServiceImpl(PortfolioService portfolioService, TenantRepository repository) {
    this.portfolioService = portfolioService;
    this.repository = repository;
  }

  @Override
  public List<Tenant> listTenants() {
    return portfolioService.getPortfolio().getTenants();
  }

  @Override
  public Tenant createTenant(Tenant tenant) {
    Portfolio portfolio = portfolioService.getPortfolio();
    tenant.setPortfolio(portfolio);
    return repository.save(tenant);
  }

  @Override
  public Tenant getTenant(long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Tenant not found"));
  }

  @Override
  public Tenant updateTenant(long id, Tenant tenant) {
    return repository.findById(id).map(persisted -> {
      TenantUtils.merge(tenant, persisted);
      return repository.save(persisted);
    }).orElseThrow(() -> new NotFoundException("Tenant not found"));
  }
}