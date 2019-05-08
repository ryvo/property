package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Portfolio;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
class PortfolioServiceImpl implements PortfolioService {

  // TODO Determine portfolio base on authenticated user
  private static final long PORTFOLIO_ID = 1L;

  @Autowired
  private PortfolioRepository repository;

  @Override
  public Portfolio getPortfolio() {
    Optional<Portfolio> optional = repository.findById(PORTFOLIO_ID);
    return optional.orElseThrow(() -> new NotFoundException("Portfolio not found"));
  }

  @Override
  public Portfolio savePortfolio(Portfolio portfolio) {
    return repository.save(portfolio);
  }
}
