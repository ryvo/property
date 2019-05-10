package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.Owner;

import static java.util.Objects.requireNonNull;

public class OwnerUtils {

  public static void merge(Owner source, Owner target) {
    requireNonNull(source, "source must not be null");
    requireNonNull(target, "target must not be null");

    target.setType(source.getType());
    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setCompanyName(source.getCompanyName());
    AddressUtils.merge(source.getAddress(), target.getAddress());
    ContactUtils.merge(source.getContact(), target.getContact());
  }
}
