package cz.ryvo.propertymanager.backend.api;

import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApiObject {
  @OutputOnly
  private Long id;
}
