package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
