package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.Contact;

import static java.util.Objects.requireNonNull;

public class ContactUtils {

  public static void merge(Contact source, Contact target) {
    requireNonNull(source, "source must not be null");
    requireNonNull(target, "target must not be null");

    target.setEmail(source.getEmail());
    target.setTelephone1(source.getTelephone1());
    target.setTelephone2(source.getTelephone2());
  }
}
