package cz.ryvo.propertymanager.backend.converter;

import cz.ryvo.propertymanager.backend.Contact;
import cz.ryvo.propertymanager.backend.api.PersonDTO;
import cz.ryvo.propertymanager.backend.domain.Address;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.domain.Person;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

abstract class PersonConverter {

  public void mergeAddress(PersonDTO source, Address target) {
    Objects.requireNonNull(target, "target must not be null");

    target.setHouseNumber(source.getHouseNumber());
    target.setRegistryNumber(source.getRegistryNumber());
    target.setStreetName(source.getStreetName());
    target.setTownName(source.getTownName());
    target.setPostalCode(source.getPostalCode());
    target.setCountryName(source.getCountryName());
  }

  public void mergeContact(PersonDTO source, Contact target) {
    Objects.requireNonNull(target, "target must not be null");

    target.setTelephone1(source.getTelephone1());
    target.setTelephone2(source.getTelephone2());
    target.setEmail(source.getEmail());
  }
}
