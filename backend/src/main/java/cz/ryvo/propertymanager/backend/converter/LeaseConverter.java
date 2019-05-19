package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.LeaseDTO;
import cz.ryvo.propertymanager.backend.domain.Lease;

public interface LeaseConverter {
  LeaseDTO toDTO(Lease domain);
  Lease toEntity(LeaseDTO dto);
}
