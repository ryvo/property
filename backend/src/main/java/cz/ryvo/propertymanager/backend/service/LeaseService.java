package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Lease;
import org.springframework.lang.NonNull;

import java.util.List;

public interface LeaseService {
  @NonNull List<Lease> listLeases(@NonNull long buildingUnitId);
  @NonNull List<Lease> listActiveLeases(@NonNull long buildingId);
  @NonNull Lease getLease(@NonNull long id);
  @NonNull Lease createLease(@NonNull Lease lease);
  @NonNull Lease updateLease(Long id, Lease lease);
}
