package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.Lease;

import static java.util.Objects.requireNonNull;

public class LeaseUtils {

  public static void merge(Lease source, Lease target) {
    requireNonNull(source, "source must not be null");
    requireNonNull(target, "target must not be null");

    target.setStartDate(source.getStartDate());
    target.setEndDate(source.getEndDate());
    target.setMonthlyRent(source.getMonthlyRent());
  }
}
