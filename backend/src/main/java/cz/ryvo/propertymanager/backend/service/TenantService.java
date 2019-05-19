package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.api.SearchTenantsCriteria;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TenantService {
  List<Tenant> searchTenants(SearchTenantsCriteria criteria);
  Tenant createTenant(@NonNull Tenant tenant);
  Tenant getTenant(long id);
  Tenant updateTenant(long id, @NonNull Tenant tenant);
}
