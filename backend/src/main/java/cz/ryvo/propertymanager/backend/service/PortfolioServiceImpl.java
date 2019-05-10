package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Portfolio;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.PortfolioRepository;
import cz.ryvo.propertymanager.backend.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class PortfolioServiceImpl implements PortfolioService {

  @Autowired
  private PortfolioRepository repository;

  @Override
  public Portfolio getPortfolio() {
    Long portfolioId = AuthUtils.getPortfolioId();
    Optional<Portfolio> optional = repository.findById(portfolioId);
    return optional.orElseThrow(() -> new NotFoundException("Portfolio not found"));
  }

  @Override
  public Portfolio savePortfolio(Portfolio portfolio) {
    return repository.save(portfolio);
  }
}
