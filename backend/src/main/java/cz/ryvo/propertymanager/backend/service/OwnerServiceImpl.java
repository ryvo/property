package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Portfolio;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.OwnerRepository;
import cz.ryvo.propertymanager.backend.util.OwnerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OwnerServiceImpl implements OwnerService {

  private final PortfolioService portfolioService;

  private final OwnerRepository repository;

  @Autowired
  public OwnerServiceImpl(PortfolioService portfolioService, OwnerRepository repository) {
    this.portfolioService = portfolioService;
    this.repository = repository;
  }

  @Override
  public List<Owner> listOwners() {
    return portfolioService.getPortfolio().getOwners();
  }

  @Override
  public Owner createOwner(Owner owner) {
    Portfolio portfolio = portfolioService.getPortfolio();
    owner.setPortfolio(portfolio);
    return repository.save(owner);
  }

  @Override
  public Owner getOwner(long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Owner not found"));
  }

  @Override
  public Owner updateOwner(long id, Owner owner) {
    return repository.findById(id).map(persisted -> {
      OwnerUtils.merge(owner, persisted);
      return repository.save(persisted);
    }).orElseThrow(() -> new NotFoundException("Owner not found"));
  }
}