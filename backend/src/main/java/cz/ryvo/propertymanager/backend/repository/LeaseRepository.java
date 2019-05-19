package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
  List<Lease> findAllByBuildingUnitIdOrderByStartDateDesc(long buildingUnitId);

  @Query("from Lease as lease"
      + " inner join lease.buildingUnit as buildingUnit"
      + " inner join buildingUnit.building as building"
      + " inner join building.portfolio"
      + " where building.id=:buildingId"
      + "   and lease.startDate <= current_date"
      + "   and (lease.endDate is null or lease.endDate >= current_date)")
  List<Lease> findAllByBuildingId(long buildingUnitId);
}
