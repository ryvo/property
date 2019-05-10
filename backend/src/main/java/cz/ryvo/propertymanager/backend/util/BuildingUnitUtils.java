package cz.ryvo.propertymanager.backend.util;

import cz.ryvo.propertymanager.backend.domain.BuildingUnit;

import static java.util.Objects.requireNonNull;

public class BuildingUnitUtils {

  public static void merge(BuildingUnit source, BuildingUnit target) {
    requireNonNull(source, "source must not be null");
    requireNonNull(target, "target must not be null");

    target.setType(source.getType());
    target.setName(source.getName());
    target.setStorey(source.getStorey());
    target.setArea(source.getArea());
  }
}
