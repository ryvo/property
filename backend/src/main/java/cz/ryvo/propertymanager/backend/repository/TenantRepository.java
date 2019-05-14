package cz.ryvo.propertymanager.backend.repository;

import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
