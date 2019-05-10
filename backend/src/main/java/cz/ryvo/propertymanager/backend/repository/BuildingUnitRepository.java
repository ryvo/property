package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.BuildingUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingUnitRepository extends JpaRepository<BuildingUnit, Long> {
}
