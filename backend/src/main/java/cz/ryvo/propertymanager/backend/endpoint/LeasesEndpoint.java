package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.LeaseDTO;
import cz.ryvo.propertymanager.backend.converter.LeaseConverter;
import cz.ryvo.propertymanager.backend.converter.LeasesConverter;
import cz.ryvo.propertymanager.backend.domain.Lease;
import cz.ryvo.propertymanager.backend.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional(readOnly = true)
@RestController
@RequestMapping(path = "/api/v1")
public class LeasesEndpoint {

  private final LeaseService leaseService;
  private final LeaseConverter leaseConverter;
  private final LeasesConverter leasesConverter;

  @Autowired
  public LeasesEndpoint(
      LeaseService leaseService,
      LeaseConverter leaseConverter,
      LeasesConverter leasesConverter) {
    this.leaseService = leaseService;
    this.leaseConverter = leaseConverter;
    this.leasesConverter = leasesConverter;
  }

  @GetMapping(path = "/building-units/{buildingUnitId}/leases")
  public List<LeaseDTO> listLeases(@PathVariable("buildingUnitId") long buildingUnitId) {
    return leasesConverter.toDTO(leaseService.listLeases(buildingUnitId));
  }

  @Transactional
  @PostMapping(path = "/building-units/{buildingUnitId}/leases")
  public LeaseDTO createLease(@PathVariable("buildingUnitId") long buildingUnitId, @RequestBody @Validated LeaseDTO lease) {
    return leaseConverter.toDTO(leaseService.createLease(buildingUnitId, leaseConverter.toEntity(lease)));
  }

  @Transactional
  @PutMapping(path = "/leases/{id}")
  public LeaseDTO updateLease(@PathVariable("id") long id, @RequestBody @Validated LeaseDTO lease) {
    return leaseConverter.toDTO(leaseService.updateLease(id, leaseConverter.toEntity(lease)));
  }
}
