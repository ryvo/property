package cz.ryvo.propertymanager.backend.endpoint;

import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.converter.OwnerConverter;
import cz.ryvo.propertymanager.backend.converter.OwnersConverter;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional(readOnly = true)
@RestController
@RequestMapping(path = "/api/v1/owners")
public class OwnersEndpoint {

  private final OwnerConverter ownerConverter;

  private final OwnersConverter ownersConverter;

  private final OwnerService service;

  @Autowired
  public OwnersEndpoint(
      OwnerConverter ownerConverter,
      OwnersConverter ownersConverter,
      OwnerService service) {
    this.ownerConverter = ownerConverter;
    this.ownersConverter = ownersConverter;
    this.service = service;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<PersonDTO> listOwners() {
    return ownersConverter.toDTO(service.listOwners());
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public PersonDTO createOwner(@RequestBody @Validated PersonDTO dto) {
    Owner owner = ownerConverter.toEntity(dto);
    return ownerConverter.toDTO(service.createOwner(owner));
  }

  @Transactional
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public PersonDTO updateOwner(@PathVariable("id") long id, @RequestBody @Validated PersonDTO dto) {
    Owner owner = ownerConverter.toEntity(dto);
    return ownerConverter.toDTO(service.updateOwner(id, owner));
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  public PersonDTO getBuilding(@PathVariable("id") long id) {
    return ownerConverter.toDTO(service.getOwner(id));
  }
}