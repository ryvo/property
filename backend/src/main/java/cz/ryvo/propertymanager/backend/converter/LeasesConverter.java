package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.LeaseDTO;
import cz.ryvo.propertymanager.backend.domain.Lease;

import java.util.List;

public interface LeasesConverter {
  List<LeaseDTO> toDTO(List<Lease> domain);
  List<Lease> toEntity(List<LeaseDTO> dto);
}
