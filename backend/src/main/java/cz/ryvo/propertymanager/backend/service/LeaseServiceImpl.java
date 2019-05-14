package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LeaseServiceImpl implements LeaseService {

  private final LeaseRepository repository;

  @Autowired
  public LeaseServiceImpl(LeaseRepository repository) {
    this.repository = repository;
  }

  public List<Lease> listLeases(@NonNull long buildingUnitId) {

  }

  @NonNull List<Lease> listActiveLeases(@NonNull long buildingId);
  @NonNull Lease getLease(@NonNull long id);
  @NonNull Lease createLease(@NonNull Lease lease);
  @NonNull Lease updateLease(Long id, Lease lease);
}
