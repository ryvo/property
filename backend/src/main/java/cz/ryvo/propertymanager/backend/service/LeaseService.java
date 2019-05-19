package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.exception.ServerException;
import org.springframework.lang.NonNull;

import java.util.List;

public interface LeaseService {
  @NonNull List<Lease> listLeases(long buildingUnitId);
  @NonNull List<Lease> listActiveLeases(long buildingId);
  @NonNull Lease getLease(long id);
  @NonNull Lease createLease(long buildingUnitId, @NonNull Lease lease);
  @NonNull Lease updateLease(long id, @NonNull Lease lease);
}
