package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
