package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.Contact;
import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Address;
import cz.ryvo.propertymanager.backend.domain.Person;

import static java.util.Objects.requireNonNull;

public class PersonUtils {

  public static void mergeEntityIntoDTO(Person entity, PersonDTO dto) {
    requireNonNull(entity, "entity must not be null");
    requireNonNull(dto, "dto must not be null");

    dto.setId(entity.getId());
    dto.setType(entity.getType());
    dto.setCompanyName(entity.getCompanyName());
    dto.setLastName(entity.getLastName());
    dto.setFirstName(entity.getFirstName());
    if (entity.getAddress() != null) {
      dto.setHouseNumber(entity.getAddress().getHouseNumber());
      dto.setRegistryNumber(entity.getAddress().getRegistryNumber());
      dto.setStreetName(entity.getAddress().getStreetName());
      dto.setTownName(entity.getAddress().getTownName());
      dto.setPostalCode(entity.getAddress().getPostalCode());
      dto.setCountryName(entity.getAddress().getCountryName());
    }
    if (entity.getContact() != null) {
      dto.setEmail(entity.getContact().getEmail());
      dto.setTelephone1(entity.getContact().getTelephone1());
      dto.setTelephone2(entity.getContact().getTelephone2());
    }
  }

  public static void mergeDtoIntoEntity(PersonDTO dto, Person entity) {
    requireNonNull(dto, "dto must not be null");
    requireNonNull(entity, "entity must not be null");

    entity.setId(dto.getId());
    entity.setType(dto.getType());
    entity.setCompanyName(dto.getCompanyName());
    entity.setLastName(dto.getLastName());
    entity.setFirstName(dto.getFirstName());

    Address address = entity.getAddress();
    address.setHouseNumber(dto.getHouseNumber());
    address.setRegistryNumber(dto.getRegistryNumber());
    address.setStreetName(dto.getStreetName());
    address.setTownName(dto.getTownName());
    address.setPostalCode(dto.getPostalCode());
    address.setCountryName(dto.getCountryName());

    Contact contact = entity.getContact();
    contact.setEmail(dto.getEmail());
    contact.setTelephone1(dto.getTelephone1());
    contact.setTelephone2(dto.getTelephone2());
  }
}
