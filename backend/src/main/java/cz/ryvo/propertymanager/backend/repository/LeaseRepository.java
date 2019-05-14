package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
  List<Lease> findAllByBuildingUnitId(long buildingUnitId);
  List<Lease> findAllByBuildingId(long buildingUnitId);
}
