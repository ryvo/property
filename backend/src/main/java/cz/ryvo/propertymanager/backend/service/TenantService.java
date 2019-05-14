package cz.ryvo.propertymanager.backend.service;

import cz.ryvo.propertymanager.backend.domain.Tenant;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TenantService {
  List<Tenant> listTenants();
  Tenant createTenant(@NonNull Tenant tenant);
  Tenant getTenant(long id);
  Tenant updateTenant(long id, @NonNull Tenant tenant);
}
