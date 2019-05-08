package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Portfolio;

public interface PortfolioService {
  Portfolio getPortfolio();
  Portfolio savePortfolio(Portfolio portfolio);
}
