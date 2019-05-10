package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.Address;

import static java.util.Objects.requireNonNull;

public class AddressUtils {

  public static void merge(Address source, Address target) {
    requireNonNull(source, "source must not be null");
    requireNonNull(target, "target must not be null");

    target.setHouseNumber(source.getHouseNumber());
    target.setRegistryNumber(source.getRegistryNumber());
    target.setStreetName(source.getStreetName());
    target.setTownName(source.getTownName());
    target.setPostalCode(source.getPostalCode());
    target.setCountryName(source.getCountryName());
  }
}
