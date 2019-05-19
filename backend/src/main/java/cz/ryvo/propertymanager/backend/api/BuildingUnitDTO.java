package cz.ryvo.propertymanager.backend.api;

import cz.ryvo.propertymanager.backend.domain.BuildingUnitType;
import cz.ryvo.propertymanager.backend.domain.Owner;
import cz.ryvo.propertymanager.backend.validation.constraint.OutputOnly;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
public class BuildingUnitDTO extends ApiObject {

  @NotBlank
  private String name;

  @NotNull
  private BuildingUnitType type;

  private Integer area;

  private Integer storey;
}
