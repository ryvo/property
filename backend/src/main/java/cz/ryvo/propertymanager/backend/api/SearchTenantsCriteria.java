package cz.ryvo.propertymanager.backend.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTenantsCriteria {
  private Long buildingId;
  private String name;
}
