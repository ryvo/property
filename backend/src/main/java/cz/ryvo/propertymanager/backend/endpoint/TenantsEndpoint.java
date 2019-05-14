package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.converter.TenantConverter;
import cz.ryvo.propertymanager.backend.converter.TenantsConverter;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Tenant;
import cz.ryvo.propertymanager.backend.service.TenantService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional(readOnly = true)
@RestController
@RequestMapping(path = "/api/v1/tenants")
public class TenantsEndpoint {

  private final TenantService service;
  private final TenantConverter tenantConverter;
  private final TenantsConverter tenantsConverter;

  public TenantsEndpoint(TenantService service, TenantConverter tenantConverter, TenantsConverter tenantsConverter) {
    this.service = service;
    this.tenantConverter = tenantConverter;
    this.tenantsConverter = tenantsConverter;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<PersonDTO> listTenants() {
    return tenantsConverter.toDTO(service.listTenants());
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public PersonDTO createTenant(@RequestBody @Validated PersonDTO dto) {
    Tenant tenant = tenantConverter.toEntity(dto);
    return tenantConverter.toDTO(service.createTenant(tenant));
  }

  @Transactional
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public PersonDTO updateTenant(@PathVariable("id") long id, @RequestBody @Validated PersonDTO dto) {
    Tenant tenant = tenantConverter.toEntity(dto);
    return tenantConverter.toDTO(service.updateTenant(id, tenant));
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public PersonDTO getTenant(@PathVariable("id") long id) {
    return tenantConverter.toDTO(service.getTenant(id));
  }
}
