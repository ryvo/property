package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.api.LeaseDTO;
import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class LeaseConverterImpl implements LeaseConverter {

  @Override
  public LeaseDTO toDTO(@NonNull Lease entity) {
    LeaseDTO dto = new LeaseDTO();
    dto.setId(entity.getId());
    dto.setStartDate(entity.getStartDate());
    dto.setEndDate(entity.getEndDate());
    dto.setMonthlyRent(entity.getMonthlyRent());
    dto.setTenantId(entity.getTenant().getId());
    return dto;
  }

  @Override
  public Lease toEntity(@NonNull LeaseDTO dto) {
    Tenant tenant = new Tenant();
    tenant.setId(dto.getTenantId());

    Lease entity = new Lease();
    entity.setStartDate(dto.getStartDate());
    entity.setEndDate(dto.getEndDate());
    entity.setMonthlyRent(dto.getMonthlyRent());
    entity.setTenant(tenant);
    return entity;
  }
}
